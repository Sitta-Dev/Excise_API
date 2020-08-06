package com.xcs.phase2.dao.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasProductSubBrand;
import com.xcs.phase2.request.master.MasProductSubBrandgetByConReq;
import com.xcs.phase2.request.master.MasProductSubBrandupdDeleteReq;
import com.xcs.phase2.response.master.MasProductSubBrandinsAllResponse;
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
public class MasProductSubBrandDAOImpl extends MasterExt implements MasProductSubBrandDAO {

    private static final Logger log = LoggerFactory.getLogger(MasProductBrandDAOImpl.class);

    @Override
    public List<MasProductSubBrand> MasProductSubBrandgetByCon(MasProductSubBrandgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT " +
                        "        PRODUCT_SUBBRAND_ID," +
                        "        PRODUCT_SUBBRAND_CODE," +
                        "        PRODUCT_SUBBRAND_NAME_TH," +
                        "        PRODUCT_SUBBRAND_NAME_EN," +
                        "        IS_ACTIVE," +
//                        "        CREATE_DATE," +
//                        "        CREATE_USER_ACCOUNT_ID," +
//                        "        UPDATE_DATE," +
//                        "        UPDATE_USER_ACCOUNT_ID," +
                        "        PRODUCT_GROUP_CODE," +
                        "        PRODUCT_BRAND_CODE," +
                        "        PRODUCT_CATEGORY_CODE" +
                        "    FROM MAS_PRODUCT_SUBBRAND" +
                        "    WHERE  1=1 ");

        if(!StringUtils.isEmpty(req.getTEXT_SEARCH())){
            sqlBuilder.append("  AND   (" +
                    "        LOWER(MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_TH) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                    "        OR LOWER(MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_NAME_EN) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                    "    )" );
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_SUBBRAND_ID())){
            sqlBuilder.append(" AND MAS_PRODUCT_SUBBRAND.PRODUCT_SUBBRAND_ID = '"+req.getPRODUCT_SUBBRAND_ID()+"' ");
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_GROUP_CODE())){
            sqlBuilder.append("  AND MAS_PRODUCT_SUBBRAND.PRODUCT_GROUP_CODE = '"+req.getPRODUCT_GROUP_CODE()+"' ");
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_CATEGORY_CODE())){
            sqlBuilder.append(" AND MAS_PRODUCT_SUBBRAND.PRODUCT_CATEGORY_CODE = '"+req.getPRODUCT_CATEGORY_CODE()+"' ");
        }

        //sqlBuilder.append(" GROUP BY MAS_PRODUCT_BRAND.PRODUCT_BRAND_ID,MAS_PRODUCT_BRAND.BRAND_NAME ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductSubBrand> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductSubBrand mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductSubBrand item = new MasProductSubBrand();
                item.setPRODUCT_SUBBRAND_ID(rs.getInt("PRODUCT_SUBBRAND_ID"));
                item.setPRODUCT_SUBBRAND_CODE(rs.getString("PRODUCT_SUBBRAND_CODE"));
                item.setPRODUCT_SUBBRAND_NAME_TH(rs.getString("PRODUCT_SUBBRAND_NAME_TH"));
                item.setPRODUCT_SUBBRAND_NAME_EN(rs.getString("PRODUCT_SUBBRAND_NAME_EN"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
//                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
//                item.setCREATE_USER_ACCOUNT_ID(rs.getLong("CREATE_USER_ACCOUNT_ID"));
//                item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
//                item.setUPDATE_USER_ACCOUNT_ID(rs.getLong("UPDATE_USER_ACCOUNT_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_BRAND_CODE(rs.getString("PRODUCT_BRAND_CODE"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                return item;
            }
        });
        return dataList;
    }

    @Override
    public MasProductSubBrandinsAllResponse MasProductSubBrandinsAll(MasProductSubBrand req) {
    	
    	MasProductSubBrandinsAllResponse res = new MasProductSubBrandinsAllResponse();
    	
    	try {
    		String PRODUCT_SUBBRAND_ID = getSequences("SELECT MAS_PRODUCT_SUBBRAND_SEQ.NEXTVAL FROM DUAL");
    		
    		log.info("[getSequences] PRODUCT_SUBBRAND_ID : "+PRODUCT_SUBBRAND_ID);
    		
    		StringBuilder sqlBuilder = new StringBuilder()
			    .append("Insert into MAS_PRODUCT_SUBBRAND (" +
			    		"PRODUCT_SUBBRAND_ID," +
			    		"PRODUCT_SUBBRAND_CODE," +
			    		"PRODUCT_SUBBRAND_NAME_TH," +
			    		"PRODUCT_SUBBRAND_NAME_EN," +
			    		"IS_ACTIVE," +
			    		"PRODUCT_GROUP_CODE," +
			    		"PRODUCT_BRAND_CODE, " +
			    		"PRODUCT_CATEGORY_CODE) " +
			    		"values (" +
			    		"'"+PRODUCT_SUBBRAND_ID+"'," +
	    	    		"'"+req.getPRODUCT_SUBBRAND_CODE()+"'," +
	    	    		"'"+req.getPRODUCT_SUBBRAND_NAME_TH()+"'," +
			    		"'"+req.getPRODUCT_SUBBRAND_NAME_EN()+"'," +
			    		"'"+req.getIS_ACTIVE()+"', " +
			    		"'"+req.getPRODUCT_GROUP_CODE()+"', " +
			    		"'"+req.getPRODUCT_BRAND_CODE()+"', " +
			    		"'"+req.getPRODUCT_CATEGORY_CODE()+"' )");
    		
    		
				log.info("[SQL MasProductSubBrandinsAllResponse] : "+sqlBuilder.toString());
				
				getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
	            res.setPRODUCT_SUBBRAND_ID(Integer.parseInt(PRODUCT_SUBBRAND_ID));

	            res.setIsSuccess(Message.TRUE);
	            res.setMsg(Message.COMPLETE);

	            return res;
    		
    	} catch (Exception e){
    		e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setPRODUCT_SUBBRAND_ID(0);
            return res;		
    	}

    	
    }

    @Override
    public Boolean MasProductSubBrandupdAll(MasProductSubBrand req) {
    	
    	try { 		
    		StringBuilder sqlBuilderSub = new StringBuilder()
			    .append("UPDATE MAS_PRODUCT_SUBBRAND SET "
			    		+"PRODUCT_SUBBRAND_CODE = '"+req.getPRODUCT_SUBBRAND_CODE()+"', " 
			    		+"PRODUCT_SUBBRAND_NAME_TH = '"+req.getPRODUCT_SUBBRAND_NAME_TH()+"', "
			    		+"PRODUCT_SUBBRAND_NAME_EN = '"+req.getPRODUCT_SUBBRAND_NAME_EN()+"', "
			    		+"IS_ACTIVE = '" +req.getIS_ACTIVE()+"',"
			    		+"PRODUCT_GROUP_CODE = '" +req.getPRODUCT_GROUP_CODE()+"', "
			    		+"PRODUCT_BRAND_CODE = '" +req.getPRODUCT_BRAND_CODE()+"', "
			    		+"PRODUCT_CATEGORY_CODE = '" +req.getPRODUCT_CATEGORY_CODE()+"' "	    		
			    		+"WHERE PRODUCT_SUBBRAND_ID = '"+req.getPRODUCT_SUBBRAND_ID()+"'");

				log.info("[SQL MasProductSubBrandupdAll] : "+sqlBuilderSub.toString());
				
				getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
	            return true;
    		
    	} catch (Exception e){
    		e.printStackTrace();
            return false;		
    	}
    }

    @Override
    public Boolean MasProductSubBrandupdDelete(MasProductSubBrandupdDeleteReq req) {
    	StringBuilder sqlBuilder = new StringBuilder().append("UPDATE MAS_PRODUCT_SUBBRAND SET IS_ACTIVE = '0' WHERE PRODUCT_SUBBRAND_ID = '" + req.getPRODUCT_SUBBRAND_ID() + "' ");	
        
    	getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
    	
        return true;
    }
}
