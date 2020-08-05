package com.xcs.phase2.dao.other;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.other.Version;
import com.xcs.phase2.request.other.CheckVersionReq;
import com.xcs.phase2.request.other.DownloadNewVersionReq;
import com.xcs.phase2.request.other.UpdateDeleteVersionReq;
import com.xcs.phase2.response.Other.InsertNewVersionResponse;
import com.xcs.phase2.storage.StorageException;
import com.xcs.phase2.storage.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class VersionDAOImpl extends OtherExt implements VersionDAO {

    private static final Logger log = LoggerFactory.getLogger(VersionDAOImpl.class);


    private final Path rootLocationApk;

    @Autowired
    public VersionDAOImpl(StorageProperties properties) {
        this.rootLocationApk = Paths.get(properties.getUploadApk()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.rootLocationApk);
        } catch (Exception ex) {
            throw new StorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public List<Version> CheckVersion(CheckVersionReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select * from OPS_VERSION where VERSION_TYPE = '"+req.getVERSION_TYPE()+"' AND IS_ACTIVE = 1  ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<Version> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public Version mapRow(ResultSet rs, int rowNum) throws SQLException {
                Version item = new Version();
                item.setVERSION_ID(rs.getInt("VERSION_ID"));
                item.setFILE_PATH(rs.getString("FILE_PATH"));
                item.setVERSION_TYPE(rs.getInt("VERSION_TYPE"));
                item.setVERSION_NAME(rs.getString("VERSION_NAME"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;

    }

    @Override
    public Version DownloadNewVersion(DownloadNewVersionReq req) {



        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select * from OPS_VERSION where VERSION_ID = '"+req.getVERSION_ID()+"' AND IS_ACTIVE = 1  ");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Version>() {

            public Version extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Version item = new Version();
                    item.setVERSION_ID(rs.getInt("VERSION_ID"));
                    item.setFILE_PATH(rs.getString("FILE_PATH"));
                    item.setVERSION_TYPE(rs.getInt("VERSION_TYPE"));
                    item.setVERSION_NAME(rs.getString("VERSION_NAME"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public InsertNewVersionResponse InsertNewVersion(Version req) {

        InsertNewVersionResponse res = new InsertNewVersionResponse();

        Path targetLocation = null;
        // Normalize file name
        String fileName = StringUtils.cleanPath(req.getFILE().getOriginalFilename());
        try
        {
            if (req.getFILE().isEmpty())
            {
                throw new StorageException("Failed to store empty file " + req.getFILE().getOriginalFilename());
            }
            // Copy file to the target location (Replacing existing file with the same name)

            targetLocation = this.rootLocationApk.resolve(fileName);
            Files.copy(req.getFILE().getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        }
        catch (IOException e)
        {
            throw new StorageException("Failed to store file " + req.getFILE().getOriginalFilename(), e);
        }


        String VERSION_ID = getSequences("SELECT OPS_VERSION_SEQ.NEXTVAL FROM DUAL");
        StringBuilder sqlBuilder = new StringBuilder()
                .append("Insert into OPS_VERSION ( " +
                        "VERSION_ID," +
                        "FILE_PATH," +
                        "VERSION_TYPE," +
                        "VERSION_NAME," +
                        "IS_ACTIVE" +
                        " ) values (" +
                        "'" + VERSION_ID + "'," +
                        "'" + targetLocation + "'," +
                        "'" + req.getVERSION_TYPE() + "'," +
                        "'" + req.getVERSION_NAME() + "'," +
                        "'" + 1 + "'" +
                        " )");


        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
        res.setVERSION_ID(Integer.parseInt(VERSION_ID));

        res.setIsSuccess(Message.TRUE);
        res.setMsg(Message.COMPLETE);

        return res;

    }

    @Override
    public Boolean UpdateDeleteVersion(UpdateDeleteVersionReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_VERSION SET IS_ACTIVE = '0' where VERSION_ID = '"+req.getVERSION_ID()+"'  ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        return true;
    }
}
