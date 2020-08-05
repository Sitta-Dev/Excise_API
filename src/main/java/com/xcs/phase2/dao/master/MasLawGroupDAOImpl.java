package com.xcs.phase2.dao.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.master.MasLawGroup;
import com.xcs.phase2.request.master.MasLawGroupgetByConAdvReq;
import com.xcs.phase2.request.master.MasLawGroupgetByConReq;
import com.xcs.phase2.request.master.MasLawGroupgetByKeywordReq;
import com.xcs.phase2.request.master.MasLawGroupupdDeleteReq;
import com.xcs.phase2.response.master.MasLawGroupinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MasLawGroupDAOImpl extends MasterExt implements MasLawGroupDAO {

    private static final Logger log = LoggerFactory.getLogger(MasLawGroupDAOImpl.class);


    @Override
    public List<MasLawGroup> MasLawGroupgetByKeyword(MasLawGroupgetByKeywordReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT " +
                        "    LAW_GROUP_ID," +
                        "    LAW_GROUP_NO," +
                        "    LAW_GROUP_NAME," +
                        "    PART_NO," +
                        "    PART_NAME," +
                        "    IS_ACTIVE," +
                        "   to_char(EFFECTIVE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as EFFECTIVE_DATE," +
                        "   to_char(EXPIRE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as EXPIRE_DATE" +
                        "    FROM MAS_LAW_GROUP" +
                        "    WHERE MAS_LAW_GROUP.IS_ACTIVE = 1" +
                        "    AND LOWER (MAS_LAW_GROUP.LAW_GROUP_NAME) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    OR LOWER (MAS_LAW_GROUP.PART_NAME) like lower(replace ('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "    ORDER BY MAS_LAW_GROUP.LAW_GROUP_NO asc" );

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasLawGroup> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasLawGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasLawGroup item = new MasLawGroup();
                item.setLAW_GROUP_ID(rs.getInt("LAW_GROUP_ID"));
                item.setLAW_GROUP_NO(rs.getString("LAW_GROUP_NO"));
                item.setLAW_GROUP_NAME(rs.getString("LAW_GROUP_NAME"));
                item.setPART_NO(rs.getString("PART_NO"));
                item.setPART_NAME(rs.getString("PART_NAME"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setEFFECTIVE_DATE(rs.getString("EFFECTIVE_DATE"));
                item.setEXPIRE_DATE(rs.getString("EXPIRE_DATE"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<MasLawGroup> MasLawGroupgetByConAdv(MasLawGroupgetByConAdvReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT " +
                        "    LAW_GROUP_ID," +
                        "    LAW_GROUP_NO," +
                        "    LAW_GROUP_NAME," +
                        "    PART_NO," +
                        "    PART_NAME," +
                        "    IS_ACTIVE," +
                        "   to_char(EFFECTIVE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as EFFECTIVE_DATE," +
                        "   to_char(EXPIRE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as EXPIRE_DATE" +
                        "    FROM MAS_LAW_GROUP" +
                        "    WHERE MAS_LAW_GROUP.IS_ACTIVE = 1" +
                        "    AND LOWER (MAS_LAW_GROUP.LAW_GROUP_NAME) like lower(replace ('%"+req.getLAW_GROUP_NAME()+"%',' ',''))" +
                        "    OR LOWER (MAS_LAW_GROUP.PART_NAME) like lower(replace ('%"+req.getPART_NAME()+"%',' ',''))" +
                        "    ORDER BY MAS_LAW_GROUP.LAW_GROUP_NO asc" );

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasLawGroup> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasLawGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasLawGroup item = new MasLawGroup();
                item.setLAW_GROUP_ID(rs.getInt("LAW_GROUP_ID"));
                item.setLAW_GROUP_NO(rs.getString("LAW_GROUP_NO"));
                item.setLAW_GROUP_NAME(rs.getString("LAW_GROUP_NAME"));
                item.setPART_NO(rs.getString("PART_NO"));
                item.setPART_NAME(rs.getString("PART_NAME"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setEFFECTIVE_DATE(rs.getString("EFFECTIVE_DATE"));
                item.setEXPIRE_DATE(rs.getString("EXPIRE_DATE"));
                return item;
            }
        });

        return dataList;

    }

    @Override
    public MasLawGroup MasLawGroupgetByCon(MasLawGroupgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT " +
                        "    LAW_GROUP_ID," +
                        "    LAW_GROUP_NO," +
                        "    LAW_GROUP_NAME," +
                        "    PART_NO," +
                        "    PART_NAME," +
                        "    IS_ACTIVE," +
                        "   to_char(EFFECTIVE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as EFFECTIVE_DATE," +
                        "   to_char(EXPIRE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as EXPIRE_DATE" +
                        "    FROM MAS_LAW_GROUP " +
                        "    WHERE MAS_LAW_GROUP.IS_ACTIVE = 1 " +
                        "    ORDER BY MAS_LAW_GROUP.LAW_GROUP_NO asc" );

        log.info("[SQL] : " + sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasLawGroup>() {

            public MasLawGroup extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasLawGroup item = new MasLawGroup();
                    item.setLAW_GROUP_ID(rs.getInt("LAW_GROUP_ID"));
                    item.setLAW_GROUP_NO(rs.getString("LAW_GROUP_NO"));
                    item.setLAW_GROUP_NAME(rs.getString("LAW_GROUP_NAME"));
                    item.setPART_NO(rs.getString("PART_NO"));
                    item.setPART_NAME(rs.getString("PART_NAME"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                    item.setEFFECTIVE_DATE(rs.getString("EFFECTIVE_DATE"));
                    item.setEXPIRE_DATE(rs.getString("EXPIRE_DATE"));
                    return item;

                }

                return null;
            }
        });
    }

    @Override
    public MasLawGroupinsAllResponse MasLawGroupinsAll(MasLawGroup req) {

        MasLawGroupinsAllResponse res = new MasLawGroupinsAllResponse();

        try {

            String LAW_GROUP_ID = getSequences("SELECT MAS_LAW_GROUP_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into MAS_LAW_GROUP ( " +
                            "LAW_GROUP_ID," +
                            "LAW_GROUP_NO," +
                            "LAW_GROUP_NAME," +
                            "PART_NO," +
                            "PART_NAME," +
                            "EFFECTIVE_DATE," +
                            "EXPIRE_DATE," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + LAW_GROUP_ID + "'," +
                            "'" + req.getLAW_GROUP_NO() + "'," +
                            "'" + req.getLAW_GROUP_NAME() + "'," +
                            "'" + req.getPART_NO() + "'," +
                            "'" + req.getPART_NAME() + "'," +
                            "TO_TIMESTAMP_TZ('" + req.getEFFECTIVE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "TO_TIMESTAMP_TZ('" + req.getEXPIRE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), " +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setLAW_GROUP_ID(Integer.parseInt(LAW_GROUP_ID));


            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setLAW_GROUP_ID(0);
            return res;
        }
    }

    @Override
    public Boolean MasMasLawGroupupdAll(MasLawGroup req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE MAS_LAW_GROUP SET "
                        + "LAW_GROUP_NO=  '" + req.getLAW_GROUP_NO() + "', "
                        + "LAW_GROUP_NAME=  '" + req.getLAW_GROUP_NAME() + "', "
                        + "PART_NO=  '" + req.getPART_NO() + "', "
                        + "PART_NAME=  '" + req.getPART_NAME() + "', "
                        + " EFFECTIVE_DATE = TO_TIMESTAMP_TZ('" + req.getEFFECTIVE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + " EXPIRE_DATE = TO_TIMESTAMP_TZ('" + req.getEXPIRE_DATE() + "', '" + Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE + "'), "
                        + "IS_ACTIVE=  '" + req.getIS_ACTIVE() + "' "
                        + " WHERE LAW_GROUP_ID = '" + req.getLAW_GROUP_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});



        return true;
    }

    @Override
    public Boolean MasLawGroupupdDelete(MasLawGroupupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE MAS_LAW_GROUP SET IS_ACTIVE = '0' WHERE LAW_GROUP_ID = '" + req.getLAW_GROUP_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});
        return true;
    }
}
