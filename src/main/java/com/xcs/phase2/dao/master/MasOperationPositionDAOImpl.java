package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasOperationPosition;
import com.xcs.phase2.request.master.MasOperationPositionGetByConReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MasOperationPositionDAOImpl extends MasterExt implements MasOperationPositionDAO{

    private static final Logger log = LoggerFactory.getLogger(MasOperationPositionDAOImpl.class);


    @Override
    public List<MasOperationPosition> MasOperationPositionGetByCon(MasOperationPositionGetByConReq req) {

        String str = "";

        if(!StringUtils.isEmpty(req.getOPERATION_POS_ID())){

            str = " AND OPERATION_POS_ID = '"+req.getOPERATION_POS_ID()+"'";
        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  SELECT *" +
                        "  FROM MAS_OPERATION_POSITION" +
                        "  WHERE " +
                        "  (" +
                        "    LOWER(OPERATION_POS_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "  )" +
                        "  AND IS_ACTIVE = 1"+str);

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasOperationPosition> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasOperationPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasOperationPosition item = new MasOperationPosition();
                item.setOPERATION_POS_ID(rs.getInt("OPERATION_POS_ID"));
                item.setOPERATION_POS_CODE(rs.getString("OPERATION_POS_CODE"));
                item.setOPERATION_POS_NAME(rs.getString("OPERATION_POS_NAME"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                return item;
            }
        });

        return dataList;

    }
}
