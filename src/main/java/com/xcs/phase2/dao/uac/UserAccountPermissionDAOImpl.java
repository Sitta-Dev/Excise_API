package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.UserAccountPermission;
import com.xcs.phase2.request.uac.UserAccountPermissionCheckPermissionReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserAccountPermissionDAOImpl extends UacExt implements UserAccountPermissionDAO {

    private static final Logger log = LoggerFactory.getLogger(UserAccountPermissionDAOImpl.class);

    @Override
    public List<UserAccountPermission> UserAccountPermissionCheckPermission(UserAccountPermissionCheckPermissionReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "USER_PERMISSION_ID," +
                        "USER_ACCOUNT_ID," +
                        "PROGRAM_CODE," +
                        "IS_CREATE," +
                        "IS_READ," +
                        "IS_UPDATE," +
                        "IS_DELETE " +
                        " from UAC_USER_PERMISSION  where  ");
        sqlBuilder.append(" USER_ACCOUNT_ID = '"+req.getUSER_ACCOUNT_ID()+"' and PROGRAM_CODE = '"+req.getPROGRAM_CODE()+"' ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<UserAccountPermission> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public UserAccountPermission mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserAccountPermission item = new UserAccountPermission();
                item.setUSER_PERMISSION_ID(rs.getInt("USER_PERMISSION_ID"));
                item.setUSER_ACCOUNT_ID(rs.getInt("USER_ACCOUNT_ID"));
                item.setPROGRAM_CODE(rs.getString("PROGRAM_CODE"));
                item.setIS_CREATE(rs.getInt("IS_CREATE"));
                item.setIS_READ(rs.getInt("IS_READ"));
                item.setIS_UPDATE(rs.getInt("IS_UPDATE"));
                item.setIS_DELETE(rs.getInt("IS_DELETE"));

                return item;
            }
        });

        return dataList;

    }
}
