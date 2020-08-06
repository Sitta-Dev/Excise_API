package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductType;
import com.xcs.phase2.request.master.MasProductTypegetByConReq;
import com.xcs.phase2.request.master.MasProductTypeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductTypeinsAllResponse;
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
public class MasProductTypeDAOImpl extends MasterExt implements MasProductTypeDAO{

    private static final Logger log = LoggerFactory.getLogger(MasProductTypeDAOImpl.class);

    @Override
    public List<MasProductType> MasProductTypegetByCon(MasProductTypegetByConReq req) {
    	
    	 StringBuilder sqlBuilder = new StringBuilder()
                 .append("    SELECT DISTINCT " +
                         "    PRODUCT_TYPE_ID, " +
                         "    PRODUCT_CATEGORY_ID, " +
                         "    PRODUCT_TYPE_CODE, " +
                         "    PRODUCT_TYPE_NAME, " +
                         "    IS_TAX, " +
                         "    IS_ACTIVE " +
                         "    FROM MAS_PRODUCT_TYPE WHERE 1=1 ");

         if(!StringUtils.isEmpty(req.getTEXT_SEARCH())){
             sqlBuilder.append("  AND   (" +
                     "        LOWER(PRODUCT_TYPE_NAME) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +")");
         }

         if(req.getPRODUCT_CATEGORY_ID() != 0 && !"".equals(req.getPRODUCT_CATEGORY_ID())){
             sqlBuilder.append(" AND PRODUCT_CATEGORY_ID  = '"+req.getPRODUCT_CATEGORY_ID()+"' ");
         }

         if(req.getPRODUCT_TYPE_ID() != 0 && !"".equals(req.getPRODUCT_TYPE_ID())){
             sqlBuilder.append(" AND PRODUCT_TYPE_ID  = '"+req.getPRODUCT_TYPE_ID ()+"' ");
         }

         if(!StringUtils.isEmpty(req.getPRODUCT_TYPE_CODE())){
             sqlBuilder.append(" AND PRODUCT_TYPE_CODE  = '"+req.getPRODUCT_TYPE_CODE()+"' ");
         }

         log.info("[SQL MasProductTypegetByCon ]  : " + sqlBuilder.toString());

         @SuppressWarnings("unchecked")
         List<MasProductType> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

             public MasProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
            	 MasProductType item = new MasProductType();
                 item.setPRODUCT_TYPE_ID(rs.getInt("PRODUCT_TYPE_ID"));
                 item.setPRODUCT_CATEGORY_ID(rs.getInt("PRODUCT_CATEGORY_ID"));
                 item.setPRODUCT_TYPE_CODE(rs.getString("PRODUCT_TYPE_CODE"));
                 item.setPRODUCT_TYPE_NAME(rs.getString("PRODUCT_TYPE_NAME"));
                 item.setIS_TAX(rs.getInt("IS_TAX"));
                 item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));       
                 
                 return item;
             }
         });

         return dataList;

  
    }

    @Override
    public MasProductTypeinsAllResponse MasProductTypeinsAll(MasProductType req) {
        return null;
    }

    @Override
    public Boolean MasProductTypeupdAll(MasProductType req) {
        return null;
    }

    @Override
    public Boolean MasProductTypeupdDelete(MasProductTypeupdDeleteReq req) {
        return null;
    }
}

