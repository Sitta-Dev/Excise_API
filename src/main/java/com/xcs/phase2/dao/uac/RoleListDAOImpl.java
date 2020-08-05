package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.RoleList;
import com.xcs.phase2.request.uac.RoleListgetByConAdvReq;
import com.xcs.phase2.request.uac.RoleListgetByKeywordReq;
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
public class RoleListDAOImpl extends UacExt implements RoleListDAO {

    private static final Logger log = LoggerFactory.getLogger(RoleListDAOImpl.class);

    @Override
    public List<RoleList> RoleListgetByKeyword(RoleListgetByKeywordReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "ROLE_ID," +
                        "ROLE_CODE," +
                        "ROLE_NAME," +
                        "ROLE_DESCRIPTION," +
                        "OPERATION_POS_ID," +
                        "IS_ACTIVE" +
                        " from UAC_ROLE  where IS_ACTIVE = 1 ");
        sqlBuilder.append(" and" +
                " ( lower(ROLE_NAME) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ','')) " +
                "   or lower(ROLE_CODE) like lower(replace('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                " ) order by ROLE_ID asc");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RoleList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RoleList mapRow(ResultSet rs, int rowNum) throws SQLException {
                RoleList item = new RoleList();
                item.setROLE_ID(rs.getInt("ROLE_ID"));
                item.setROLE_CODE(rs.getString("ROLE_CODE"));
                item.setROLE_NAME(rs.getString("ROLE_NAME"));
                item.setROLE_DESCRIPTION(rs.getString("ROLE_DESCRIPTION"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setMasOperationPosition(getMasOperationPosition(rs.getInt("OPERATION_POS_ID")));


                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<RoleList> RoleListgetByConAdv(RoleListgetByConAdvReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "ROLE_ID," +
                        "ROLE_CODE," +
                        "ROLE_NAME," +
                        "ROLE_DESCRIPTION," +
                        "OPERATION_POS_ID," +
                        "IS_ACTIVE" +
                        " from UAC_ROLE  where IS_ACTIVE = 1 ");

        if(req.getROLE_CODE() != null && !"".equals(req.getROLE_CODE())) {
            sqlBuilder.append(" AND LOWER(ROLE_CODE) LIKE LOWER(REPLACE('%"+req.getROLE_CODE()+"%',' ','')) ");
        }

        if(req.getROLE_NAME() != null && !"".equals(req.getROLE_NAME())) {
            sqlBuilder.append(" AND LOWER(ROLE_NAME) LIKE LOWER(REPLACE('%"+req.getROLE_NAME()+"%',' ','')) ");
        }

        sqlBuilder.append(" order by ROLE_ID asc");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<RoleList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public RoleList mapRow(ResultSet rs, int rowNum) throws SQLException {
                RoleList item = new RoleList();
                item.setROLE_ID(rs.getInt("ROLE_ID"));
                item.setROLE_CODE(rs.getString("ROLE_CODE"));
                item.setROLE_NAME(rs.getString("ROLE_NAME"));
                item.setROLE_DESCRIPTION(rs.getString("ROLE_DESCRIPTION"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

                item.setMasOperationPosition(getMasOperationPosition(rs.getInt("OPERATION_POS_ID")));

                return item;
            }
        });

        return dataList;

    }
}
