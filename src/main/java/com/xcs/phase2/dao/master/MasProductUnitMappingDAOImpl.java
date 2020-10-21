package com.xcs.phase2.dao.master;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasProductUnitMapping;
import com.xcs.phase2.response.master.MasProductUnitMappingResponse;
import com.xcs.phase2.response.master.MasProductUnitMappinginsAllResponse;

@Service
@Transactional
public class MasProductUnitMappingDAOImpl  extends MasterExt implements MasProductUnitMappingDAO {
	private static final Logger log = LoggerFactory.getLogger(MasProductUnitMappingDAOImpl.class);
	
	@Override
    public MasProductUnitMappinginsAllResponse MasProductUnitMappinginsAll(List<MasProductUnitMapping> req) {
    	
		MasProductUnitMappinginsAllResponse res = new MasProductUnitMappinginsAllResponse();
    	
		if(req != null) {
	    	log.info("[Sub] Size : "+req.size());
	    	List<MasProductUnitMappingResponse> list = new ArrayList<MasProductUnitMappingResponse>();
		    if(req.size() > 0){
		    	
		    	for(MasProductUnitMapping item : req){
		    		
		    		String UNIT_MAPPING_ID = getSequences("SELECT MAS_PRODUCT_UNIT_MAPPING_SEQ1.NEXTVAL FROM DUAL");
		    		MasProductUnitMappingResponse  obj = new MasProductUnitMappingResponse();
		    		obj.setUNIT_MAPPING_ID(Integer.parseInt(UNIT_MAPPING_ID));
		
    	
		    		StringBuilder sqlBuilder = new StringBuilder()
		    				.append(" INSERT INTO MAS_PRODUCT_UNIT_MAPPING ("+
		    						" UNIT_MAPPING_ID,"+ 
		    						" PRODUCT_GROUP_CODE,"+
		    						" UNIT_CODE,"+
		    						" USED_FOR,"+ 
		    						" IS_ACTIVE,"+ 
		    						" CREATE_DATE,"+
		    						" CREATE_USER_ACCOUNT_ID,"+
		    						" UPDATE_DATE,"+
		    						" UPDATE_USER_ACCOUNT_ID,"+
		    						" PRODUCT_CATEGORY_CODE)"+
		    						" VALUES ("+
		    						" '"+UNIT_MAPPING_ID+"',"+
		    						" '"+item.getPRODUCT_GROUP_CODE()+"',"+
		    						" '"+item.getUNIT_CODE()+"',"+
		    						" '"+item.getUSED_FOR()+"',"+
		    						" 1,"+
		    						" (SELECT SYSTIMESTAMP FROM dual),"+
		    						" NULL,"+
		    						" NULL,"+
		    						" NULL,"+
		    						" NULL )" );
		    		log.info("[SQL MasProductUnitMappinginsAll]  : " + sqlBuilder.toString());
    	
		    		getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

	    
		    		list.add(obj);
    	
		    	}
		    }
		    res.setMasProductUnitMappingResponse(list);
		}

        res.setIsSuccess(Message.TRUE);
        res.setMsg(Message.COMPLETE);
    	
        return res;
    }
	
	@Override
    public Boolean MasProductUnitMappingupdAll(List<MasProductUnitMapping> req) {
    	    	
		if(req != null) {
	    	log.info("[Sub] Size : "+req.size());
		    if(req.size() > 0){
		    	
		    	for(MasProductUnitMapping item : req){
		    		    	
		    		StringBuilder sqlBuilder = new StringBuilder()
		    				.append(" UPDATE MAS_PRODUCT_UNIT_MAPPING"+
		    						" SET PRODUCT_GROUP_CODE = '"+item.getPRODUCT_GROUP_CODE()+"',"+
		    						" USED_FOR = '"+item.getUSED_FOR()+"',"+
		    						" UPDATE_DATE = (SELECT SYSTIMESTAMP FROM dual)"+
		    						" WHERE UNIT_MAPPING_ID = "+item.getUNIT_MAPPING_ID());
		    		log.info("[SQL MasProductUnitMappingupdAll]  : " + sqlBuilder.toString());
    	
		    		getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

		    	}
		    }
		}
		
        return true;
    }
	
	@Override
    public Boolean MasProductUnitMappingupdDelete(List<MasProductUnitMapping> req) {
    	    	
		if(req != null) {
	    	log.info("[Sub] Size : "+req.size());
		    if(req.size() > 0){
		    	
		    	for(MasProductUnitMapping item : req){
		    		    	
		    		StringBuilder sqlBuilder = new StringBuilder()
		    				.append(" UPDATE MAS_PRODUCT_UNIT_MAPPING"+
		    						" SET IS_ACTIVE = 0 ,"+
		    						" UPDATE_DATE = (SELECT SYSTIMESTAMP FROM dual)"+
		    						" WHERE UNIT_MAPPING_ID = "+item.getUNIT_MAPPING_ID());
		    		log.info("[SQL MasProductUnitMappingupdDelete]  : " + sqlBuilder.toString());
    	
		    		getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});

		    	}
		    }
		}
		
        return true;
    }

}
