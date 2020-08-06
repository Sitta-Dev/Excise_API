package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductSubType;
import com.xcs.phase2.request.master.MasProductSubTypegetByConReq;
import com.xcs.phase2.request.master.MasProductSubTypeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductSubTypeinsAllResponse;
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
public class MasProductSubTypeDAOImpl extends MasterExt implements MasProductSubTypeDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductSubTypeDAOImpl.class);

    @Override
    public List<MasProductSubType> MasProductSubTypegetByCon(MasProductSubTypegetByConReq req) {
        
    	StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT MAS_PRODUCT_SUBTYPE.*" +
                        "    FROM MAS_PRODUCT_SUBTYPE WHERE 1=1 ");

        if(!StringUtils.isEmpty(req.getTEXT_SEARCH())){
            sqlBuilder.append("  AND   (" +
                    "        LOWER(PRODUCT_SUBTYPE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +")");
        }

        if(req.getPRODUCT_SUBTYPE_ID() != 0 && !"".equals(req.getPRODUCT_SUBTYPE_ID())){
            sqlBuilder.append(" AND PRODUCT_SUBTYPE_ID  = '"+req.getPRODUCT_SUBTYPE_ID()+"' ");
        }

        if(req.getPRODUCT_TYPE_ID() != 0 && !"".equals(req.getPRODUCT_TYPE_ID())){
            sqlBuilder.append(" AND PRODUCT_TYPE_ID  = '"+req.getPRODUCT_TYPE_ID()+"' ");
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_SUBTYPE_CODE())){
            sqlBuilder.append(" AND PRODUCT_SUBTYPE_CODE  = '"+req.getPRODUCT_SUBTYPE_CODE()+"' ");
        }

        log.info("[SQL MasProductTypegetByCon ]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductSubType> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductSubType mapRow(ResultSet rs, int rowNum) throws SQLException {
            	MasProductSubType item = new MasProductSubType();
                item.setPRODUCT_SUBTYPE_ID(rs.getInt("PRODUCT_SUBTYPE_ID"));
                item.setPRODUCT_TYPE_ID(rs.getInt("PRODUCT_TYPE_ID"));
                item.setPRODUCT_SUBTYPE_CODE(rs.getString("PRODUCT_SUBTYPE_CODE"));
                item.setPRODUCT_SUBTYPE_NAME(rs.getString("PRODUCT_SUBTYPE_NAME"));
                item.setIS_TAX(rs.getInt("IS_TAX"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));       
                
                return item;
            }
        });

        return dataList;
    		
    }

    @Override
    public MasProductSubTypeinsAllResponse MasProductSubTypeinsAll(MasProductSubType req) {
        return null;
    }

    @Override
    public Boolean MasProductSubTypeupdAll(MasProductSubType req) {
        return null;
    }

    @Override
    public Boolean MasProductSubTypeupdDelete(MasProductSubTypeupdDeleteReq req) {
        return null;
    }
}
