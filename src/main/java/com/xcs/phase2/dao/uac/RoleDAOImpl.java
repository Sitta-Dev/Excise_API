package com.xcs.phase2.dao.uac;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.uac.Role;
import com.xcs.phase2.model.uac.RolePermission;
import com.xcs.phase2.request.uac.RolegetByConReq;
import com.xcs.phase2.request.uac.RoleupdDeleteReq;
import com.xcs.phase2.response.uac.RolePermissionResponse;
import com.xcs.phase2.response.uac.RoleinsAllResponse;
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
public class RoleDAOImpl extends UacExt implements RoleDAO {

    private static final Logger log = LoggerFactory.getLogger(RoleDAOImpl.class);

    @Override
    public Role RolegetByCon(RolegetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "ROLE_ID," +
                        "ROLE_CODE," +
                        "ROLE_NAME," +
                        "ROLE_DESCRIPTION," +
                        "OPERATION_POS_ID," +
                        "IS_ACTIVE" +
                        " from UAC_ROLE  where IS_ACTIVE = 1 ");
        sqlBuilder.append("and ROLE_ID = '"+req.getROLE_ID()+"'");

        log.info("[SQL] : "+sqlBuilder.toString());

        return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<Role>() {

            public Role extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {

                    Role item = new Role();
                    item.setROLE_ID(rs.getInt("ROLE_ID"));
                    item.setROLE_CODE(rs.getString("ROLE_CODE"));
                    item.setROLE_NAME(rs.getString("ROLE_NAME"));
                    item.setROLE_DESCRIPTION(rs.getString("ROLE_DESCRIPTION"));
                    item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                    item.setRolePermission(getRolePermission(rs.getInt("ROLE_ID")));
                    item.setMasOperationPosition(getMasOperationPosition(rs.getInt("OPERATION_POS_ID")));

                    return item;
                }

                return null;
            }
        });
    }

    @Override
    public RoleinsAllResponse RoleinsAll(Role req) {

        RoleinsAllResponse res = new RoleinsAllResponse();

        try {

            String ROLE_ID = getSequences("SELECT UAC_ROLE_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into UAC_ROLE ( " +
                            "ROLE_ID," +
                            "ROLE_CODE," +
                            "ROLE_NAME," +
                            "ROLE_DESCRIPTION," +
                            "OPERATION_POS_ID," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + ROLE_ID + "'," +
                            "'"+req.getROLE_CODE()+"'," +
                            "'"+req.getROLE_NAME()+"'," +
                            "'"+req.getROLE_DESCRIPTION()+"'," +
                            "'"+req.getOPERATION_POS_ID()+"'," +
                            "'" + req.getIS_ACTIVE() + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setROLE_ID(Integer.parseInt(ROLE_ID));


            if (req.getRolePermission() != null) {
                log.info("[Sub] Size : " + req.getRolePermission().size());
                List<RolePermissionResponse> list = new ArrayList<RolePermissionResponse>();

                if (req.getRolePermission().size() > 0) {
                    for (RolePermission item : req.getRolePermission()) {

                        String ROLE_PERMISSION_ID = getSequences("SELECT UAC_ROLE_PERMISSION_SEQ.NEXTVAL FROM DUAL");
                        RolePermissionResponse obj = new RolePermissionResponse();
                        obj.setROLE_PERMISSION_ID(Integer.parseInt(ROLE_PERMISSION_ID));

                        StringBuilder sqlBuilderSub = new StringBuilder()
                                .append("Insert into UAC_ROLE_PERMISSION ( " +
                                        "ROLE_PERMISSION_ID," +
                                        "ROLE_ID," +
                                        "MODULE_DETAIL_ID," +
                                        "MODULE_ID," +
                                        "IS_CREATE," +
                                        "IS_READ," +
                                        "IS_UPDATE," +
                                        "IS_DELETE" +
                                        " ) values (" +
                                        "'" + ROLE_PERMISSION_ID + "'," +
                                        "'" + ROLE_ID + "'," +
                                        "'"+item.getMODULE_DETAIL_ID()+"'," +
                                        "'"+item.getMODULE_ID()+"'," +
                                        "'"+item.getIS_CREATE()+"'," +
                                        "'"+item.getIS_READ()+"'," +
                                        "'"+item.getIS_UPDATE()+"'," +
                                        "'"+item.getIS_DELETE()+"'" +
                                        " )");
                        log.info("[SQL] : " + sqlBuilderSub.toString());

                        getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                        list.add(obj);
                    }
                }
                res.setRolePermission(list);
            }

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setROLE_ID(0);
            res.setRolePermission(null);
            return res;
        }
    }

    @Override
    public Boolean RoleupdByCon(Role req) {


        StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE UAC_ROLE SET "
                        + "ROLE_CODE=  '"+req.getROLE_CODE()+"', "
                        + "ROLE_NAME=  '"+req.getROLE_NAME()+"', "
                        + "ROLE_DESCRIPTION=  '"+req.getROLE_DESCRIPTION()+"', "
                        + "OPERATION_POS_ID=  '"+req.getOPERATION_POS_ID()+"', "
                        + "IS_ACTIVE='" + req.getIS_ACTIVE() + "' "
                        + " WHERE ROLE_ID = '" + req.getROLE_ID() + "' ");

        log.info("[SQL] : " + sqlBuilder.toString());
        getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

        if (req.getRolePermission() != null) {
            log.info("[Sub] Size : " + req.getRolePermission().size());

            if (req.getRolePermission().size() > 0) {
                for (RolePermission item : req.getRolePermission()) {

                    StringBuilder sqlBuilderSub = new StringBuilder()
                            .append("UPDATE UAC_ROLE_PERMISSION SET "
                                    + "ROLE_ID=  '"+item.getROLE_ID()+"', "
                                    + "MODULE_DETAIL_ID=  '"+item.getMODULE_DETAIL_ID()+"', "
                                    + "MODULE_ID=  '"+item.getMODULE_ID()+"', "
                                    + "IS_CREATE=  '"+item.getIS_CREATE()+"', "
                                    + "IS_READ=  '"+item.getIS_READ()+"', "
                                    + "IS_UPDATE=  '"+item.getIS_UPDATE()+"', "
                                    + "IS_DELETE=  '"+item.getIS_DELETE()+"' "
                                    + " WHERE ROLE_PERMISSION_ID = '" + item.getROLE_PERMISSION_ID() + "' ");
                    log.info("[SQL] : " + sqlBuilderSub.toString());

                    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
                }
            }
        }

        return true;
    }

    @Override
    public Boolean RoleupdDelete(RoleupdDeleteReq req) {

        StringBuilder sqlBuilder1 = new StringBuilder().append("DELETE FROM UAC_ROLE  WHERE ROLE_ID = '"+req.getROLE_ID()+"' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
        return true;
    }
}
