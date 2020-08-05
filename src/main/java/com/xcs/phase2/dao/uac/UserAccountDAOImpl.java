package com.xcs.phase2.dao.uac;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.uac.UserAccount;
import com.xcs.phase2.model.uac.UserAccountPermission;
import com.xcs.phase2.request.uac.UserAccountgetByConReq;
import com.xcs.phase2.request.uac.UserAccountupdDeleteReq;
import com.xcs.phase2.response.uac.UserAccountPermissionResponse;
import com.xcs.phase2.response.uac.UserAccountinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserAccountDAOImpl extends UacExt implements UserAccountDAO {

    private static final Logger log = LoggerFactory.getLogger(RoleDAOImpl.class);

    @Override
    public UserAccount UserAccountgetByCon(UserAccountgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "USER_ACCOUNT_ID," +
                        "STAFF_ID," +
                        "ROLE_ID," +
                        "USER_TYPE," +
                        "USER_NAME," +
                        "PASSWORD," +
                        "IS_SIGN_ON," +
                        "SIGN_ON_IP," +
                        "APPROVE_CODE," +
                        "IS_ACTIVE" +
                        " from UAC_USER_ACCOUNT  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and USER_ACCOUNT_ID = '"+req.getUSER_ACCOUNT_ID()+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<UserAccount>() {

            public UserAccount extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    UserAccount item = new UserAccount();
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

                    item.setUserAccountPermission(getUserAccountPermission(rs.getInt("USER_ACCOUNT_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public UserAccountinsAllResponse UserAccountinsAll(UserAccount req) {

        UserAccountinsAllResponse res = new UserAccountinsAllResponse();

        try {

            String USER_ACCOUNT_ID = getSequences("SELECT UAC_USER_ACCOUNT_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into UAC_USER_ACCOUNT ( " +
                            "USER_ACCOUNT_ID," +
                            "STAFF_ID," +
                            "ROLE_ID," +
                            "USER_TYPE," +
                            "USER_NAME," +
                            "PASSWORD," +
                            "IS_SIGN_ON," +
                            "SIGN_ON_IP," +
                            "APPROVE_CODE," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + USER_ACCOUNT_ID + "'," +
                            "'" + req.getSTAFF_ID() + "'," +
                            "'" + req.getROLE_ID() + "'," +
                            "'" + req.getUSER_TYPE() + "'," +
                            "'" + req.getUSER_NAME() + "'," +
                            "'" + req.getPASSWORD() + "'," +
                            "'" + req.getIS_SIGN_ON() + "'," +
                            "'" + req.getSIGN_ON_IP() + "'," +
                            "'" + req.getAPPROVE_CODE() + "'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setUSER_ACCOUNT_ID(Integer.parseInt(USER_ACCOUNT_ID));


            if (req.getUserAccountPermission() != null) {
                log.info("[Sub] Size : " + req.getUserAccountPermission().size());
                List<UserAccountPermissionResponse> list = new ArrayList<UserAccountPermissionResponse>();

                if (req.getUserAccountPermission().size() > 0) {
                    for (UserAccountPermission item : req.getUserAccountPermission()) {

                        String USER_PERMISSION_ID = getSequences("SELECT UAC_USER_PERMISSION_SEQ.NEXTVAL FROM DUAL");
                        UserAccountPermissionResponse obj = new UserAccountPermissionResponse();
                        obj.setUSER_PERMISSION_ID(Integer.parseInt(USER_PERMISSION_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into UAC_USER_PERMISSION ( " +
                                        "USER_PERMISSION_ID," +
                                        "USER_ACCOUNT_ID," +
                                        "PROGRAM_CODE," +
                                        "IS_CREATE," +
                                        "IS_READ," +
                                        "IS_UPDATE," +
                                        "IS_DELETE " +
                                        " ) values (" +
                                        "'" + USER_PERMISSION_ID + "'," +
                                        "'" + USER_ACCOUNT_ID + "'," +
                                        "'" + item.getPROGRAM_CODE() + "'," +
                                        "'" + item.getIS_CREATE() + "'," +
                                        "'" + item.getIS_READ() + "'," +
                                        "'" + item.getIS_UPDATE() + "'," +
                                        "'" + item.getIS_DELETE() + "'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setUserAccountPermission(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setUSER_ACCOUNT_ID(0);
            res.setUserAccountPermission(null);
            return res;
        }
    }

    @Override
    public Boolean UserAccountupdByCon(UserAccount req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE UAC_USER_ACCOUNT SET "
                        + "STAFF_ID=  '" + req.getSTAFF_ID() + "', "
                        + "ROLE_ID=  '" + req.getROLE_ID() + "', "
                        + "USER_TYPE=  '" + req.getUSER_TYPE() + "', "
                        + "USER_NAME=  '" + req.getUSER_NAME() + "', "
                        + "PASSWORD=  '" + req.getPASSWORD() + "', "
                        + "IS_SIGN_ON=  '" + req.getIS_SIGN_ON() + "', "
                        + "SIGN_ON_IP=  '" + req.getSIGN_ON_IP() + "', "
                        + "APPROVE_CODE=  '" + req.getAPPROVE_CODE() + "', "
                        + "IS_ACTIVE='" + req.getIS_ACTIVE() + "' "
                        + " WHERE USER_ACCOUNT_ID = '" + req.getUSER_ACCOUNT_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        if (req.getUserAccountPermission() != null) {
            log.info("[Sub] Size : " + req.getUserAccountPermission().size());

            if (req.getUserAccountPermission().size() > 0) {
                for (UserAccountPermission item : req.getUserAccountPermission()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE UAC_USER_PERMISSION SET "
                                    + "USER_ACCOUNT_ID=  '"+item.getUSER_ACCOUNT_ID()+"', "
                                    + "PROGRAM_CODE=  '"+item.getPROGRAM_CODE()+"', "
                                    + "IS_CREATE=  '"+item.getIS_CREATE()+"', "
                                    + "IS_READ=  '"+item.getIS_READ()+"', "
                                    + "IS_UPDATE=  '"+item.getIS_UPDATE()+"', "
                                    + "IS_DELETE=  '"+item.getIS_DELETE()+"' "
                                    + " WHERE USER_PERMISSION_ID = '" + item.getUSER_PERMISSION_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                }
            }
        }

        return true;
    }

    @Override
    public Boolean UserAccountupdDelete(UserAccountupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("DELETE FROM UAC_USER_ACCOUNT  WHERE USER_ACCOUNT_ID = '"+req.getUSER_ACCOUNT_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        return true;
    }
}
