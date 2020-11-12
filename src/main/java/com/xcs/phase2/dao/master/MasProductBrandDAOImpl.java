package com.xcs.phase2.dao.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasProductBrand;
import com.xcs.phase2.request.master.MasProductBrandgetByConReq;
import com.xcs.phase2.request.master.MasProductBrandupdDeleteReq;
import com.xcs.phase2.response.master.MasProductBrandinsAllResponse;

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
public class MasProductBrandDAOImpl extends MasterExt implements MasProductBrandDAO {

    private static final Logger log = LoggerFactory.getLogger(MasProductBrandDAOImpl.class);


    @Override
    public List<MasProductBrand> MasProductBrandgetByCon(MasProductBrandgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT DISTINCT " +
                        "    PRODUCT_BRAND_ID, " +
                        "    PRODUCT_BRAND_CODE, " +
                        "    PRODUCT_BRAND_NAME_TH, " +
                        "    PRODUCT_BRAND_NAME_EN, " +
                        "    IS_ACTIVE, " +
                        "    PRODUCT_GROUP_CODE, " +
                        "    PRODUCT_CATEGORY_CODE" +
                        "    FROM MAS_PRODUCT_BRAND WHERE IS_ACTIVE =1 ");

        if(!StringUtils.isEmpty(req.getTEXT_SEARCH())){
            sqlBuilder.append("  AND   (" +
                    "        LOWER(PRODUCT_BRAND_NAME_TH) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                    "        or LOWER(PRODUCT_BRAND_NAME_EN) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                    "    )" );
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_BRAND_ID())){
            sqlBuilder.append(" AND MAS_PRODUCT_BRAND.PRODUCT_BRAND_ID = '"+req.getPRODUCT_BRAND_ID()+"' ");
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_GROUP_CODE())){
            sqlBuilder.append(" AND MAS_PRODUCT_BRAND.PRODUCT_GROUP_CODE = '"+req.getPRODUCT_GROUP_CODE()+"' ");
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_CATEGORY_CODE())){
            sqlBuilder.append(" AND MAS_PRODUCT_BRAND.PRODUCT_CATEGORY_CODE = '"+req.getPRODUCT_CATEGORY_CODE()+"' ");
        }

        //sqlBuilder.append(" GROUP BY MAS_PRODUCT_BRAND.PRODUCT_BRAND_ID,MAS_PRODUCT_BRAND.BRAND_NAME ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductBrand> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductBrand mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductBrand item = new MasProductBrand();
                item.setPRODUCT_BRAND_ID(rs.getInt("PRODUCT_BRAND_ID"));
                item.setPRODUCT_BRAND_CODE(rs.getString("PRODUCT_BRAND_CODE"));
                item.setPRODUCT_BRAND_NAME_TH(rs.getString("PRODUCT_BRAND_NAME_TH"));
                item.setPRODUCT_BRAND_NAME_EN(rs.getString("PRODUCT_BRAND_NAME_EN"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                


                return item;
            }
        });

        return dataList;

    }

    @Override
    public MasProductBrandinsAllResponse MasProductBrandinsAll(MasProductBrand req) {
    	
    	MasProductBrandinsAllResponse res = new MasProductBrandinsAllResponse();
    	
    	try {
    		String PRODUCT_BRAND_ID = getSequences("SELECT MAS_PRODUCT_BRAND_SEQ.NEXTVAL FROM DUAL");
    		
    		log.info("[getSequences] PRODUCT_GROUP_ID : "+PRODUCT_BRAND_ID);
    		
    		StringBuilder sqlBuilder = new StringBuilder()
			    .append("Insert into MAS_PRODUCT_BRAND (" +
			    		"PRODUCT_BRAND_ID," +
			    		"PRODUCT_BRAND_CODE," +
			    		"PRODUCT_BRAND_NAME_TH," +
			    		"PRODUCT_BRAND_NAME_EN," +
			    		"IS_ACTIVE," +
			    		"PRODUCT_GROUP_CODE," +
			    		"PRODUCT_CATEGORY_CODE) " +
			    		"values (" +
			    		"'"+PRODUCT_BRAND_ID+"'," +
	    	    		"'"+PRODUCT_BRAND_ID+"'," +
	    	    		"'"+req.getPRODUCT_BRAND_NAME_TH()+"'," +
			    		"'"+req.getPRODUCT_BRAND_NAME_EN()+"'," +
			    		"'"+req.getIS_ACTIVE()+"', " +
			    		"'"+req.getPRODUCT_GROUP_CODE()+"', " +
			    		"'"+req.getPRODUCT_CATEGORY_CODE()+"' )");
    		
    		
				log.info("[SQL MasProductBrandinsAll] : "+sqlBuilder.toString());
				
				getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
	            res.setPRODUCT_BRAND_ID(Integer.parseInt(PRODUCT_BRAND_ID));

	            res.setIsSuccess(Message.TRUE);
	            res.setMsg(Message.COMPLETE);

	            return res;
    		
    	} catch (Exception e){
    		e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setPRODUCT_BRAND_ID(0);
            return res;		
    	}

    }

    @Override
    public Boolean MasProductBrandupdAll(MasProductBrand req) {
    	
    	try { 		
    		StringBuilder sqlBuilderSub = new StringBuilder()
			    .append("UPDATE MAS_PRODUCT_BRAND SET "
			    		+"PRODUCT_BRAND_CODE = '"+req.getPRODUCT_BRAND_CODE()+"', " 
			    		+"PRODUCT_BRAND_NAME_TH = '"+req.getPRODUCT_BRAND_NAME_TH()+"', "
			    		+"PRODUCT_BRAND_NAME_EN = '"+req.getPRODUCT_BRAND_NAME_EN()+"', "
			    		+"IS_ACTIVE = '" +req.getIS_ACTIVE()+"',"
			    		+"PRODUCT_GROUP_CODE = '" +req.getPRODUCT_GROUP_CODE()+"', "
			    		+"PRODUCT_CATEGORY_CODE = '" +req.getPRODUCT_CATEGORY_CODE()+"' "
			    		+"WHERE PRODUCT_BRAND_ID = '"+req.getPRODUCT_BRAND_ID()+"'");

				log.info("[SQL MasProductBrandupdAll] : "+sqlBuilderSub.toString());
				
				getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[]{});
	            return true;
    		
    	} catch (Exception e){
    		e.printStackTrace();
            return false;		
    	}
    }

    @Override
    public Boolean MasProductBrandupdDelete(MasProductBrandupdDeleteReq req) {

    	StringBuilder sqlBuilder = new StringBuilder().append("UPDATE MAS_PRODUCT_BRAND SET IS_ACTIVE = '0' WHERE PRODUCT_BRAND_ID = '" + req.getPRODUCT_BRAND_ID() + "' ");	
        
    	getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
        return true;
    	
    }
}
