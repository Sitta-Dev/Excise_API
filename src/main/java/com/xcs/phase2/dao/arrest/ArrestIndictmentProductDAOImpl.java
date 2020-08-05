package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.arrest.ArrestIndictmentProduct;
import com.xcs.phase2.request.arrest.ArrestIndictmentProductupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestIndictmentProductResponse;
import com.xcs.phase2.response.arrest.ArrestIndictmentProductinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
@Transactional
public class ArrestIndictmentProductDAOImpl extends ArrestExt implements ArrestIndictmentProductDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestIndictmentProductDAOImpl.class);

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ArrestIndictmentProductinsAllResponse ArrestIndictmentProductinsAll(List<ArrestIndictmentProduct> req) {
		
		ArrestIndictmentProductinsAllResponse res = new ArrestIndictmentProductinsAllResponse();
		
		try {
			
			
			if(req != null) {
		    	log.info("[Sub] Size : "+req.size());
		    	List<ArrestIndictmentProductResponse> list = new ArrayList<ArrestIndictmentProductResponse>();
			    if(req.size() > 0){
			    	
			    	for(ArrestIndictmentProduct item1 : req){
			    		
			    		String PRODUCT_INDICTMENT_ID = getSequences("SELECT \"OPS_ARREST_INDICTMENT_PRODUCT_SEQ\".NEXTVAL FROM DUAL");
			    		ArrestIndictmentProductResponse  obj1 = new ArrestIndictmentProductResponse();
			    		obj1.setPRODUCT_INDICTMENT_ID(Integer.parseInt(PRODUCT_INDICTMENT_ID));

			    		StringBuilder sqlBuilderSub1 = new StringBuilder()
			    			    .append("Insert into OPS_ARREST_INDICTMENT_PRODUCT (" +
			    			    		"PRODUCT_INDICTMENT_ID," +
			    			    		"PRODUCT_ID,"+ 
			    			    		"INDICTMENT_ID," +
			    			    		"SIZES_UNIT_ID," +
			    			    		"QUATITY_UNIT_ID," +
			    			    		"VOLUMN_UNIT_ID," +
			    			    		"SIZES," +
			    			    		"SIZES_UNIT," +
			    			    		"QUANTITY," +
			    			    		"QUANTITY_UNIT," +
			    			    		"VOLUMN," +
			    			    		"VOLUMN_UNIT," +
			    			    		"FINE_ESTIMATE," +
			    			    		"IS_ILLEGAL," +
			    			    		"IS_ACTIVE) " +
			    			    		"values (" +
			    			    		"'"+PRODUCT_INDICTMENT_ID+"'," +
			    			    		"'"+item1.getPRODUCT_ID()+"'," +
			    			    		"'"+item1.getINDICTMENT_DETAIL_ID()+"'," +
			    			    		"'"+item1.getSIZES_UNIT_ID()+"'," +
			    			    		"'"+item1.getQUATITY_UNIT_ID()+"'," +
			    			    		"'"+item1.getVOLUMN_UNIT_ID()+"'," +
			    			    		"'"+item1.getSIZES()+"'," +
			    			    		"'"+item1.getSIZES_UNIT()+"'," +
			    			    		"'"+item1.getQUANTITY()+"'," +
			    			    		"'"+item1.getQUANTITY_UNIT()+"'," +
			    			    		"'"+item1.getVOLUMN()+"'," +
			    			    		"'"+item1.getVOLUMN_UNIT()+"'," +
			    			    		"'"+item1.getFINE_ESTIMATE()+"'," +
			    			    		"'"+item1.getIS_ILLEGAL()+"'," +
			    			    		"'"+item1.getIS_ACTIVE()+"')");
			    				log.info("[SQL] : "+sqlBuilderSub1.toString());
			    				
			    		getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[] {});
					    	    
					    	    list.add(obj1);
			    	}
			    }
			    res.setArrestIndictmentProduct(list);
		    }
			
			res.setIsSuccess(Message.TRUE);
		    res.setMsg(Message.COMPLETE);
		    
		    return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setIsSuccess(Message.FALSE);
		    res.setMsg(e.getMessage());
		    res.setArrestIndictmentProduct(null);
			return res;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean ArrestIndictmentProductupdByCon(List<ArrestIndictmentProduct> request) {

		if(request != null) {
	    	log.info("[Sub] Size : "+request.size());
	    	
		    if(request.size() > 0){
		    	
		    	for(ArrestIndictmentProduct req : request){
		    	
		    		StringBuilder sqlBuilderSub = new StringBuilder()
		    				.append("UPDATE OPS_ARREST_INDICTMENT_PRODUCT "
		    						+ "SET "
		    						+"PRODUCT_ID = '"+req.getPRODUCT_ID()+"',"
		    						+"INDICTMENT_ID = '"+req.getINDICTMENT_DETAIL_ID()+"',"
		    						+"SIZES_UNIT_ID = '"+req.getSIZES_UNIT_ID()+"',"
		    						+"QUATITY_UNIT_ID = '"+req.getQUATITY_UNIT_ID()+"',"
		    						+"VOLUMN_UNIT_ID = '"+req.getVOLUMN_UNIT_ID()+"',"
		    						+"SIZES = '"+req.getSIZES()+"',"
		    						+"SIZES_UNIT = '"+req.getSIZES_UNIT()+"',"
		    						+"QUANTITY = '"+req.getQUANTITY()+"',"
		    						+"QUANTITY_UNIT = '"+req.getQUANTITY_UNIT()+"',"
		    						+"VOLUMN = '"+req.getVOLUMN()+"',"
		    						+"VOLUMN_UNIT = '"+req.getVOLUMN_UNIT()+"',"
		    						+"FINE_ESTIMATE = '"+req.getFINE_ESTIMATE()+"',"
		    						+"IS_ILLEGAL = '"+req.getIS_ILLEGAL()+"',"
		    						+"IS_ACTIVE = '"+req.getIS_ACTIVE()+"' "
		    						+ "WHERE PRODUCT_INDICTMENT_ID = '"+req.getPRODUCT_INDICTMENT_ID()+"' ");
		    				log.info("[SQL] : "+sqlBuilderSub.toString());
		    				
		    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
		    	}
		    }
	    }
		
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean ArrestIndictmentProductupdDelete(List<ArrestIndictmentProductupdDeleteReq> req) {

		if(req != null) {
	    	log.info("[Sub] Size : "+req.size());
	    	
		    if(req.size() > 0){
		    	
		    	for(ArrestIndictmentProductupdDeleteReq item : req){
		    		
		    		StringBuilder sqlBuilder8 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT_PRODUCT SET IS_ACTIVE = '0' WHERE PRODUCT_INDICTMENT_ID = '"+item.getPRODUCT_INDICTMENT_ID()+"' ");
		    		
		    		getJdbcTemplate().update(sqlBuilder8.toString(), new Object[] {});
		    		
		    	}
		    }
	    }
		
		return true;
	}
}
