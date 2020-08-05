package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.UserAccountList;
import com.xcs.phase2.request.uac.UserAccountListgetByConAdvReq;
import com.xcs.phase2.request.uac.UserAccountListgetByKeywordReq;
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
public class UserAccountListDAOImpl extends UacExt implements UserAccountListDAO{

    private static final Logger log = LoggerFactory.getLogger(UserAccountListDAOImpl.class);

    @Override
    public List<UserAccountList> UserAccountListgetByKeyword(UserAccountListgetByKeywordReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select UAC_USER_ACCOUNT.* " +
                        " from UAC_USER_ACCOUNT" +
                        " left join MAS_STAFF on UAC_USER_ACCOUNT.STAFF_ID = MAS_STAFF.STAFF_ID" +
                        " where UAC_USER_ACCOUNT.IS_ACTIVE = 1" +
                        " and MAS_STAFF.IS_ACTIVE = 1" +
                        " and" +
                        " (" +
                        "  lower(MAS_STAFF.STAFF_CODE) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  or lower(UAC_USER_ACCOUNT.USER_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  or lower(MAS_STAFF.TITLE_NAME_TH||MAS_STAFF.FIRST_NAME||MAS_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  or lower(MAS_STAFF.OPREATION_POS_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  or lower(MAS_STAFF.MANAGEMENT_POS_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  or lower(MAS_STAFF.REPRESENT_POS_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  or lower(MAS_STAFF.OPERATION_DEPT_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  or lower(MAS_STAFF.OPERATION_UNDER_DEPT_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  or lower(MAS_STAFF.OPERATION_OFFICE_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  or lower(MAS_STAFF.OPERATION_OFFICE_SHORT_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        " )");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<UserAccountList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public UserAccountList mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserAccountList item = new UserAccountList();
                item.setUSER_ACCOUNT_ID(rs.getInt("USER_ACCOUNT_ID"));
                item.setSTAFF_ID(rs.getInt("STAFF_ID"));
                item.setROLE_ID(rs.getInt("ROLE_ID"));
                item.setUSER_TYPE(rs.getInt("USER_TYPE"));
                item.setUSER_NAME(rs.getString("USER_NAME"));
                item.setPASSWORD(rs.getString("PASSWORD"));
                item.setIS_SIGN_ON(rs.getInt("IS_SIGN_ON"));
                item.setSIGN_ON_IP(rs.getString("SIGN_ON_IP"));
                item.setAPPROVE_CODE(rs.getString("APPROVE_CODE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<UserAccountList> UserAccountListgetByConAdv(UserAccountListgetByConAdvReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append(" select UAC_USER_ACCOUNT.* " +
                        " from UAC_USER_ACCOUNT" +
                        " left join MAS_STAFF on UAC_USER_ACCOUNT.STAFF_ID = MAS_STAFF.STAFF_ID" +
                        " where UAC_USER_ACCOUNT.IS_ACTIVE = 1" +
                        " and MAS_STAFF.IS_ACTIVE = 1" );


        if(req.getUSER_NAME() != null && !"".equals(req.getUSER_NAME())) {
            sqlBuilder.append(" AND LOWER(UAC_USER_ACCOUNT.USER_NAME) LIKE LOWER(REPLACE('%"+req.getUSER_NAME()+"%',' ','')) ");
        }

        if(req.getSTAFF_CODE() != null && !"".equals(req.getSTAFF_CODE())) {
            sqlBuilder.append(" AND LOWER(MAS_STAFF.STAFF_CODE) LIKE LOWER(REPLACE('%"+req.getSTAFF_CODE()+"%',' ','')) ");
        }

        if(req.getSTAFF_NAME() != null && !"".equals(req.getSTAFF_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_STAFF.TITLE_NAME_TH||MAS_STAFF.FIRST_NAME||MAS_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getSTAFF_NAME()+"%',' ','')) ");
        }

        if(req.getOPREATION_POS_NAME() != null && !"".equals(req.getOPREATION_POS_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_STAFF.OPREATION_POS_NAME) LIKE LOWER(REPLACE('%"+req.getOPREATION_POS_NAME()+"%',' ','')) ");
        }

        if(req.getMANAGEMENT_POS_NAME() != null && !"".equals(req.getMANAGEMENT_POS_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_STAFF.MANAGEMENT_POS_NAME) LIKE LOWER(REPLACE('%"+req.getMANAGEMENT_POS_NAME()+"%',' ','')) ");
        }

        if(req.getREPRESENT_POS_NAME() != null && !"".equals(req.getREPRESENT_POS_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_STAFF.REPRESENT_POS_NAME) LIKE LOWER(REPLACE('%"+req.getREPRESENT_POS_NAME()+"%',' ','')) ");
        }

        if(req.getOPERATION_DEPT_NAME() != null && !"".equals(req.getOPERATION_DEPT_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_STAFF.OPERATION_DEPT_NAME) LIKE LOWER(REPLACE('%"+req.getOPERATION_DEPT_NAME()+"%',' ','')) ");
        }

        if(req.getOPERATION_UNDER_DEPT_NAME() != null && !"".equals(req.getOPERATION_UNDER_DEPT_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_STAFF.OPERATION_UNDER_DEPT_NAME) LIKE LOWER(REPLACE('%"+req.getOPERATION_UNDER_DEPT_NAME()+"%',' ','')) ");
        }

        if(req.getOPERATION_OFFICE_NAME() != null && !"".equals(req.getOPERATION_OFFICE_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_STAFF.OPERATION_OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getOPERATION_OFFICE_NAME()+"%',' ','')) ");
        }

        if(req.getOPERATION_OFFICE_SHORT_NAME() != null && !"".equals(req.getOPERATION_OFFICE_SHORT_NAME())) {
            sqlBuilder.append(" AND LOWER(MAS_STAFF.OPERATION_OFFICE_SHORT_NAME) LIKE LOWER(REPLACE('%"+req.getOPERATION_OFFICE_SHORT_NAME()+"%',' ','')) ");
        }

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<UserAccountList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public UserAccountList mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserAccountList item = new UserAccountList();
                item.setUSER_ACCOUNT_ID(rs.getInt("USER_ACCOUNT_ID"));
                item.setSTAFF_ID(rs.getInt("STAFF_ID"));
                item.setROLE_ID(rs.getInt("ROLE_ID"));
                item.setUSER_TYPE(rs.getInt("USER_TYPE"));
                item.setUSER_NAME(rs.getString("USER_NAME"));
                item.setPASSWORD(rs.getString("PASSWORD"));
                item.setIS_SIGN_ON(rs.getInt("IS_SIGN_ON"));
                item.setSIGN_ON_IP(rs.getString("SIGN_ON_IP"));
                item.setAPPROVE_CODE(rs.getString("APPROVE_CODE"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;

    }
}
