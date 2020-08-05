package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.MasOperationPosition;
import com.xcs.phase2.model.uac.ModuleDetail;
import com.xcs.phase2.model.uac.RolePermission;
import com.xcs.phase2.model.uac.UserAccountPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UacExt {

    private static final Logger log = LoggerFactory.getLogger(UacExt.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected String getSequences(String strSql) {
        log.info("[SQL]  : " + strSql);
        return getJdbcTemplate().queryForObject(strSql, String.class);
    }

    protected String getPathFile(String strSql) {
        log.info("[SQL]  : " + strSql);
        return getJdbcTemplate().queryForObject(strSql, String.class);
    }

    protected List<UserAccountPermission> getUserAccountPermission(int USER_ACCOUNT_ID) {

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
        sqlBuilder.append(" USER_ACCOUNT_ID = '"+USER_ACCOUNT_ID+"'");

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

    protected List<RolePermission> getRolePermission(int ROLE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "ROLE_PERMISSION_ID," +
                        "ROLE_ID," +
                        "MODULE_DETAIL_ID," +
                        "MODULE_ID," +
                        "IS_CREATE," +
                        "IS_READ," +
                        "IS_UPDATE," +
                        "IS_DELETE" +
                        " from UAC_ROLE_PERMISSION  where  ");
        sqlBuilder.append(" ROLE_ID = '"+ROLE_ID+"'");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RolePermission> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RolePermission mapRow(ResultSet rs, int rowNum) throws SQLException {
                RolePermission item = new RolePermission();
                item.setROLE_PERMISSION_ID(rs.getInt("ROLE_PERMISSION_ID"));
                item.setROLE_ID(rs.getInt("ROLE_ID"));
                item.setMODULE_DETAIL_ID(rs.getInt("MODULE_DETAIL_ID"));
                item.setMODULE_ID(rs.getInt("MODULE_ID"));
                item.setIS_CREATE(rs.getInt("IS_CREATE"));
                item.setIS_READ(rs.getInt("IS_READ"));
                item.setIS_UPDATE(rs.getInt("IS_UPDATE"));
                item.setIS_DELETE(rs.getInt("IS_DELETE"));

                return item;
            }
        });

        return dataList;

    }

    protected MasOperationPosition getMasOperationPosition(int OPERATION_POS_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "OPERATION_POS_ID," +
                        "OPERATION_POS_CODE," +
                        "OPERATION_POS_NAME," +
                        "IS_ACTIVE" +
                        " from MAS_OPERATION_POSITION  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and OPERATION_POS_ID = '"+OPERATION_POS_ID+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<MasOperationPosition>() {

            public MasOperationPosition extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    MasOperationPosition item = new MasOperationPosition();
                    item.setOPERATION_POS_ID(rs.getInt("OPERATION_POS_ID"));
                    item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                    item.setOPERATION_POS_NAME(rs.getString("OPERATION_POS_NAME"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));


                    return item;
                }

                return null;
            }
        });
    }

    protected List<ModuleDetail> getModuleDetail(int MODULE_ID) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select * from MAS_MODULE_DETAIL where IS_ACTIVE = 1 and MODULE_ID = "+MODULE_ID+" order by SEQUENCE");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<ModuleDetail> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public ModuleDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                ModuleDetail item = new ModuleDetail();

                item.setMODULE_DETAIL_ID(rs.getInt("MODULE_DETAIL_ID"));
                item.setMODULE_ID(rs.getInt("MODULE_ID"));
                item.setMODULE_DETAIL_CODE(rs.getString("MODULE_DETAIL_CODE"));
                item.setMODULE_DETAIL_NAME(rs.getString("MODULE_DETAIL_NAME"));
                item.setSEQUENCE(rs.getInt("SEQUENCE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));



                return item;
            }
        });

        return dataList;

    }


}
