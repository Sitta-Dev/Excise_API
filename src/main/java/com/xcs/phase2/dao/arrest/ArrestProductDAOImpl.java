package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.arrest.ArrestProduct;
import com.xcs.phase2.model.arrest.ArrestProductMapping;
import com.xcs.phase2.request.arrest.ArrestProductupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestProductMappingResponse;
import com.xcs.phase2.response.arrest.ArrestProductResponse;
import com.xcs.phase2.response.arrest.ArrestProductinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ArrestProductDAOImpl extends ArrestExt implements ArrestProductDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestProductDAOImpl.class);

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ArrestProductinsAllResponse ArrestProductinsAll(List<ArrestProduct> req) {
		
		ArrestProductinsAllResponse res = new ArrestProductinsAllResponse();
		
		try {
			
			
			if(req != null) {
		    	log.info("[Sub] Size : "+req.size());
		    	List<ArrestProductResponse> list = new ArrayList<ArrestProductResponse>();
			    if(req.size() > 0){
			    	
			    	for(ArrestProduct item : req){

			    		
			    		String PRODUCT_ID = getSequences("SELECT \"OPS_ARREST_PRODUCT_SEQ\".NEXTVAL FROM DUAL");
			    		ArrestProductResponse  obj = new ArrestProductResponse();
						obj.setPRODUCT_ID(Integer.parseInt(PRODUCT_ID));
						obj.setPRODUCT_MAPPING_ID(item.getPRODUCT_MAPPING_ID());
						obj.setPRODUCT_REF_CODE(item.getPRODUCT_REF_CODE());

			    		StringBuilder sqlBuilderSub = new StringBuilder()
			    			    .append("Insert into OPS_ARREST_PRODUCT (" +
			    			    		"PRODUCT_ID," +
			    			    		"ARREST_ID," +
			    			    		"PRODUCT_MAPPING_ID," +
			    			    		"PRODUCT_CODE," +
			    			    		"PRODUCT_REF_CODE," +
			    			    		"PRODUCT_GROUP_ID," +
			    			    		"PRODUCT_CATEGORY_ID," +
			    			    		"PRODUCT_TYPE_ID," +
			    			    		"PRODUCT_SUBTYPE_ID," +
			    			    		"PRODUCT_SUBSETTYPE_ID," +
			    			    		"PRODUCT_BRAND_ID," +
			    			    		"PRODUCT_SUBBRAND_ID," +
			    			    		"PRODUCT_MODEL_ID," +
			    			    		"PRODUCT_TAXDETAIL_ID," +
			    			    		"SIZES_UNIT_ID," +
			    			    		"QUATITY_UNIT_ID," +
			    			    		"VOLUMN_UNIT_ID," +
			    			    		"PRODUCT_GROUP_CODE," +
			    			    		"PRODUCT_GROUP_NAME," +
			    			    		"PRODUCT_CATEGORY_CODE," +
			    			    		"PRODUCT_CATEGORY_NAME," +
			    			    		"PRODUCT_TYPE_CODE," +
			    			    		"PRODUCT_TYPE_NAME," +
			    			    		"PRODUCT_SUBTYPE_CODE," +
			    			    		"PRODUCT_SUBTYPE_NAME," +
			    			    		"PRODUCT_SUBSETTYPE_CODE," +
			    			    		"PRODUCT_SUBSETTYPE_NAME," +
			    			    		"PRODUCT_BRAND_CODE," +
			    			    		"PRODUCT_BRAND_NAME_TH," +
			    			    		"PRODUCT_BRAND_NAME_EN," +
			    			    		"PRODUCT_SUBBRAND_CODE," +
			    			    		"PRODUCT_SUBBRAND_NAME_TH," +
			    			    		"PRODUCT_SUBBRAND_NAME_EN," +
			    			    		"PRODUCT_MODEL_CODE," +
			    			    		"PRODUCT_MODEL_NAME_TH," +
			    			    		"PRODUCT_MODEL_NAME_EN," +
			    			    		"IS_TAX_VALUE," +
			    			    		"TAX_VALUE," +
			    			    		"IS_TAX_VOLUMN," +
			    			    		"TAX_VOLUMN," +
			    			    		"TAX_VOLUMN_UNIT," +
			    			    		"LICENSE_PLATE," +
			    			    		"ENGINE_NO," +
			    			    		"CHASSIS_NO," +
			    			    		"PRODUCT_DESC," +
			    			    		"SUGAR," +
			    			    		"CO2," +
			    			    		"DEGREE," +
			    			    		"PRICE," +
			    			    		"SIZES," +
			    			    		"SIZES_UNIT," +
			    			    		"QUANTITY," +
			    			    		"QUANTITY_UNIT," +
			    			    		"VOLUMN," +
			    			    		"VOLUMN_UNIT," +
			    			    		"REMARK," +
			    			    		"IS_DOMESTIC," +
			    			    		"IS_ILLEGAL," +
			    			    		"IS_ACTIVE) " +
			    			    		"values (" +
			    			    		"'"+PRODUCT_ID+"'," +
					    	    		"'"+item.getARREST_ID()+"'," +
					    	    		"'"+item.getPRODUCT_MAPPING_ID()+"'," +
			    			    		"'"+item.getPRODUCT_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_REF_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_GROUP_ID()+"'," +
			    			    		"'"+item.getPRODUCT_CATEGORY_ID()+"'," +
			    			    		"'"+item.getPRODUCT_TYPE_ID()+"'," +
			    			    		"'"+item.getPRODUCT_SUBTYPE_ID()+"'," +
			    			    		"'"+item.getPRODUCT_SUBSETTYPE_ID()+"'," +
			    			    		"'"+item.getPRODUCT_BRAND_ID()+"'," +
			    			    		"'"+item.getPRODUCT_SUBBRAND_ID()+"'," +
			    			    		"'"+item.getPRODUCT_MODEL_ID()+"'," +
			    			    		"'"+item.getPRODUCT_TAXDETAIL_ID()+"'," +
			    			    		"'"+item.getSIZES_UNIT_ID()+"'," +
			    			    		"'"+item.getQUATITY_UNIT_ID()+"'," +
			    			    		"'"+item.getVOLUMN_UNIT_ID()+"'," +
			    			    		"'"+item.getPRODUCT_GROUP_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_GROUP_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_CATEGORY_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_CATEGORY_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_TYPE_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_TYPE_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_SUBTYPE_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_SUBTYPE_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_SUBSETTYPE_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_SUBSETTYPE_NAME()+"'," +
			    			    		"'"+item.getPRODUCT_BRAND_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_BRAND_NAME_TH()+"'," +
			    			    		"'"+item.getPRODUCT_BRAND_NAME_EN()+"'," +
			    			    		"'"+item.getPRODUCT_SUBBRAND_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_SUBBRAND_NAME_TH()+"'," +
			    			    		"'"+item.getPRODUCT_SUBBRAND_NAME_EN()+"'," +
			    			    		"'"+item.getPRODUCT_MODEL_CODE()+"'," +
			    			    		"'"+item.getPRODUCT_MODEL_NAME_TH()+"'," +
			    			    		"'"+item.getPRODUCT_MODEL_NAME_EN()+"'," +
			    			    		"'"+item.getIS_TAX_VALUE()+"'," +
			    			    		"'"+item.getTAX_VALUE()+"'," +
			    			    		"'"+item.getIS_TAX_VOLUMN()+"'," +
			    			    		"'"+item.getTAX_VOLUMN()+"'," +
			    			    		"'"+item.getTAX_VOLUMN_UNIT()+"'," +
			    			    		"'"+item.getLICENSE_PLATE()+"'," +
			    			    		"'"+item.getENGINE_NO()+"'," +
			    			    		"'"+item.getCHASSIS_NO()+"'," +
			    			    		"'"+item.getPRODUCT_DESC()+"'," +
			    			    		"'"+item.getSUGAR()+"'," +
			    			    		"'"+item.getCO2()+"'," +
			    			    		"'"+item.getDEGREE()+"'," +
			    			    		"'"+item.getPRICE()+"'," +
			    			    		"'"+item.getSIZES()+"'," +
			    			    		"'"+item.getSIZES_UNIT()+"'," +
			    			    		"'"+item.getQUANTITY()+"'," +
			    			    		"'"+item.getQUANTITY_UNIT()+"'," +
			    			    		"'"+item.getVOLUMN()+"'," +
			    			    		"'"+item.getVOLUMN_UNIT()+"'," +
			    			    		"'"+item.getREMARK()+"'," +
			    			    		"'"+item.getIS_DOMESTIC()+"'," +
			    			    		"'"+item.getIS_ILLEGAL()+"'," +
			    			    		"'"+item.getIS_ACTIVE()+"' )");
			    				log.info("[SQL] : "+sqlBuilderSub.toString());
			    				
			    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
			    		
			    		
			    		if(item.getArrestProductMapping() != null) {
					    	log.info("[Sub] Size : "+item.getArrestProductMapping().size());
					    	List<ArrestProductMappingResponse> list1 = new ArrayList<ArrestProductMappingResponse>();
					    	
						    if(item.getArrestProductMapping().size() > 0){
						    	for(ArrestProductMapping item1 : item.getArrestProductMapping()){
						    		
						    		String PRODUCT_MAPPING_ID = getSequences("SELECT \"OPS_ARREST_PRODUCT_MAPPING_SEQ\".NEXTVAL FROM DUAL");
						    		ArrestProductMappingResponse  obj1 = new ArrestProductMappingResponse();
									obj1.setPRODUCT_MAPPING_ID(Integer.parseInt(PRODUCT_MAPPING_ID));
									obj1.setPRODUCT_ID(Integer.parseInt(PRODUCT_ID));
									obj1.setPRODUCT_REF_CODE(item1.getPRODUCT_REF_CODE());

						    		StringBuilder sqlBuilderSub1 = new StringBuilder()
						    			    .append("Insert into OPS_ARREST_PRODUCT_MAPPING (" +
						    			    		"PRODUCT_MAPPING_ID," +
						    			    		"PRODUCT_ID," +
						    			    		"PRODUCT_MAPPING_REF_ID," +
						    			    		"PRODUCT_CODE," +
						    			    		"PRODUCT_REF_CODE," +
						    			    		"PRODUCT_GROUP_ID," +
						    			    		"PRODUCT_CATEGORY_ID," +
						    			    		"PRODUCT_TYPE_ID," +
						    			    		"PRODUCT_SUBTYPE_ID," +
						    			    		"PRODUCT_SUBSETTYPE_ID," +
						    			    		"PRODUCT_BRAND_ID," +
						    			    		"PRODUCT_SUBBRAND_ID," +
						    			    		"PRODUCT_MODEL_ID," +
						    			    		"PRODUCT_TAXDETAIL_ID," +
						    			    		"SIZES_UNIT_ID," +
						    			    		"QUATITY_UNIT_ID," +
						    			    		"VOLUMN_UNIT_ID," +
						    			    		"PRODUCT_GROUP_CODE," +
						    			    		"PRODUCT_GROUP_NAME," +
						    			    		"PRODUCT_CATEGORY_CODE," +
						    			    		"PRODUCT_CATEGORY_NAME," +
						    			    		"PRODUCT_TYPE_CODE," +
						    			    		"PRODUCT_TYPE_NAME," +
						    			    		"PRODUCT_SUBTYPE_CODE," +
						    			    		"PRODUCT_SUBTYPE_NAME," +
						    			    		"PRODUCT_SUBSETTYPE_CODE," +
						    			    		"PRODUCT_SUBSETTYPE_NAME," +
						    			    		"PRODUCT_BRAND_CODE," +
						    			    		"PRODUCT_BRAND_NAME_TH," +
						    			    		"PRODUCT_BRAND_NAME_EN," +
						    			    		"PRODUCT_SUBBRAND_CODE," +
						    			    		"PRODUCT_SUBBRAND_NAME_TH," +
						    			    		"PRODUCT_SUBBRAND_NAME_EN," +
						    			    		"PRODUCT_MODEL_CODE," +
						    			    		"PRODUCT_MODEL_NAME_TH," +
						    			    		"PRODUCT_MODEL_NAME_EN," +
						    			    		"IS_TAX_VALUE," +
						    			    		"TAX_VALUE," +
						    			    		"IS_TAX_VOLUMN," +
						    			    		"TAX_VOLUMN," +
						    			    		"TAX_VOLUMN_UNIT," +
						    			    		"LICENSE_PLATE," +
						    			    		"ENGINE_NO," +
						    			    		"CHASSIS_NO," +
						    			    		"PRODUCT_DESC," +
						    			    		"SUGAR," +
						    			    		"CO2," +
						    			    		"DEGREE," +
						    			    		"PRICE," +
						    			    		"SIZES," +
						    			    		"SIZES_UNIT," +
						    			    		"QUANTITY," +
						    			    		"QUANTITY_UNIT," +
						    			    		"VOLUMN," +
						    			    		"VOLUMN_UNIT," +
						    			    		"REMARK," +
						    			    		"PRODUCT_RESULT," +
						    			    		"SCIENCE_RESULT_DESC," +
						    			    		"VAT," +
						    			    		"IS_DOMESTIC," +
						    			    		"IS_ILLEGAL," +
						    			    		"IS_SCIENCE," +
						    			    		"IS_ACTIVE)" +
						    			    		"values (" +
						    			    		"'"+PRODUCT_MAPPING_ID+"'," +
								    	    		"'"+PRODUCT_ID+"'," +
						    			    		"'"+item1.getPRODUCT_MAPPING_REF_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_REF_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_GROUP_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_CATEGORY_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_TYPE_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBTYPE_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBSETTYPE_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_BRAND_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBBRAND_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_MODEL_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_TAXDETAIL_ID()+"'," +
						    			    		"'"+item1.getSIZES_UNIT_ID()+"'," +
						    			    		"'"+item1.getQUATITY_UNIT_ID()+"'," +
						    			    		"'"+item1.getVOLUMN_UNIT_ID()+"'," +
						    			    		"'"+item1.getPRODUCT_GROUP_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_GROUP_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_CATEGORY_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_CATEGORY_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_TYPE_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_TYPE_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBTYPE_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBTYPE_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBSETTYPE_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBSETTYPE_NAME()+"'," +
						    			    		"'"+item1.getPRODUCT_BRAND_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_BRAND_NAME_TH()+"'," +
						    			    		"'"+item1.getPRODUCT_BRAND_NAME_EN()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBBRAND_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBBRAND_NAME_TH()+"'," +
						    			    		"'"+item1.getPRODUCT_SUBBRAND_NAME_EN()+"'," +
						    			    		"'"+item1.getPRODUCT_MODEL_CODE()+"'," +
						    			    		"'"+item1.getPRODUCT_MODEL_NAME_TH()+"'," +
						    			    		"'"+item1.getPRODUCT_MODEL_NAME_EN()+"'," +
						    			    		"'"+item1.getIS_TAX_VALUE()+"'," +
						    			    		"'"+item1.getTAX_VALUE()+"'," +
						    			    		"'"+item1.getIS_TAX_VOLUMN()+"'," +
						    			    		"'"+item1.getTAX_VOLUMN()+"'," +
						    			    		"'"+item1.getTAX_VOLUMN_UNIT()+"'," +
						    			    		"'"+item1.getLICENSE_PLATE()+"'," +
						    			    		"'"+item1.getENGINE_NO()+"'," +
						    			    		"'"+item1.getCHASSIS_NO()+"'," +
						    			    		"'"+item1.getPRODUCT_DESC()+"'," +
						    			    		"'"+item1.getSUGAR()+"'," +
						    			    		"'"+item1.getCO2()+"'," +
						    			    		"'"+item1.getDEGREE()+"'," +
						    			    		"'"+item1.getPRICE()+"'," +
						    			    		"'"+item1.getSIZES()+"'," +
						    			    		"'"+item1.getSIZES_UNIT()+"'," +
						    			    		"'"+item1.getQUANTITY()+"'," +
						    			    		"'"+item1.getQUANTITY_UNIT()+"'," +
						    			    		"'"+item1.getVOLUMN()+"'," +
						    			    		"'"+item1.getVOLUMN_UNIT()+"'," +
						    			    		"'"+item1.getREMARK()+"'," +
						    			    		"'"+item1.getPRODUCT_RESULT()+"'," +
						    			    		"'"+item1.getSCIENCE_RESULT_DESC()+"'," +
						    			    		"'"+item1.getVAT()+"'," +
						    			    		"'"+item1.getIS_DOMESTIC()+"'," +
						    			    		"'"+item1.getIS_ILLEGAL()+"'," +
						    			    		"'"+item1.getIS_SCIENCE()+"'," +
						    			    		"1)");
						    				log.info("[SQL] : "+sqlBuilderSub1.toString());
						    				
						    		getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[] {});
						    		list1.add(obj1);
						    	}
						    }
						    obj.setArrestProductMapping(list1);
					    }
			    		
			    		list.add(obj);
			    	
			    	}
			    }
			    res.setArrestProduct(list);
		    }
			
			res.setIsSuccess(Message.TRUE);
		    res.setMsg(Message.COMPLETE);
		    
		    return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setIsSuccess(Message.FALSE);
		    res.setMsg(e.getMessage());
		    res.setArrestProduct(null);
			return res;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean ArrestProductupdByCon(List<ArrestProduct> request) {

		if(request != null) {
	    	log.info("[Sub] Size : "+request.size());
	    	
		    if(request.size() > 0){
		    	
		    	for(ArrestProduct req : request){
		    	
		    		StringBuilder sqlBuilderSub = new StringBuilder()
		    				.append("UPDATE OPS_ARREST_PRODUCT "
		    						+ "SET "
		    						+ "ARREST_ID = '"+req.getARREST_ID()+"', "
		    						+ "PRODUCT_MAPPING_ID = '"+req.getPRODUCT_MAPPING_ID()+"', "
		    						+ "PRODUCT_CODE = '"+req.getPRODUCT_CODE()+"', "
		    						+ "PRODUCT_REF_CODE = '"+req.getPRODUCT_REF_CODE()+"', "
		    						+ "PRODUCT_GROUP_ID = '"+req.getPRODUCT_GROUP_ID()+"', "
		    						+ "PRODUCT_CATEGORY_ID = '"+req.getPRODUCT_CATEGORY_ID()+"', "
		    						+ "PRODUCT_TYPE_ID = '"+req.getPRODUCT_TYPE_ID()+"', "
		    						+ "PRODUCT_SUBTYPE_ID = '"+req.getPRODUCT_SUBTYPE_ID()+"', "
		    						+ "PRODUCT_SUBSETTYPE_ID = '"+req.getPRODUCT_SUBSETTYPE_ID()+"', "
		    						+ "PRODUCT_BRAND_ID = '"+req.getPRODUCT_BRAND_ID()+"', "
		    						+ "PRODUCT_SUBBRAND_ID = '"+req.getPRODUCT_SUBBRAND_ID()+"', "
		    						+ "PRODUCT_MODEL_ID = '"+req.getPRODUCT_MODEL_ID()+"', "
		    						+ "PRODUCT_TAXDETAIL_ID = '"+req.getPRODUCT_TAXDETAIL_ID()+"', "
		    						+ "SIZES_UNIT_ID = '"+req.getSIZES_UNIT_ID()+"', "
		    						+ "QUATITY_UNIT_ID = '"+req.getQUATITY_UNIT_ID()+"', "
		    						+ "VOLUMN_UNIT_ID = '"+req.getVOLUMN_UNIT_ID()+"', "
		    						+ "PRODUCT_GROUP_CODE = '"+req.getPRODUCT_GROUP_CODE()+"', "
		    						+ "PRODUCT_GROUP_NAME = '"+req.getPRODUCT_GROUP_NAME()+"', "
		    						+ "PRODUCT_CATEGORY_CODE = '"+req.getPRODUCT_CATEGORY_CODE()+"', "
		    						+ "PRODUCT_CATEGORY_NAME = '"+req.getPRODUCT_CATEGORY_NAME()+"', "
		    						+ "PRODUCT_TYPE_CODE = '"+req.getPRODUCT_TYPE_CODE()+"', "
		    						+ "PRODUCT_TYPE_NAME = '"+req.getPRODUCT_TYPE_NAME()+"', "
		    						+ "PRODUCT_SUBTYPE_CODE = '"+req.getPRODUCT_SUBTYPE_CODE()+"', "
		    						+ "PRODUCT_SUBTYPE_NAME = '"+req.getPRODUCT_SUBTYPE_NAME()+"', "
		    						+ "PRODUCT_SUBSETTYPE_CODE = '"+req.getPRODUCT_SUBSETTYPE_CODE()+"', "
		    						+ "PRODUCT_SUBSETTYPE_NAME = '"+req.getPRODUCT_SUBSETTYPE_NAME()+"', "
		    						+ "PRODUCT_BRAND_CODE = '"+req.getPRODUCT_BRAND_CODE()+"', "
		    						+ "PRODUCT_BRAND_NAME_TH = '"+req.getPRODUCT_BRAND_NAME_TH()+"', "
		    						+ "PRODUCT_BRAND_NAME_EN = '"+req.getPRODUCT_BRAND_NAME_EN()+"', "
		    						+ "PRODUCT_SUBBRAND_CODE = '"+req.getPRODUCT_SUBBRAND_CODE()+"', "
		    						+ "PRODUCT_SUBBRAND_NAME_TH = '"+req.getPRODUCT_SUBBRAND_NAME_TH()+"', "
		    						+ "PRODUCT_SUBBRAND_NAME_EN = '"+req.getPRODUCT_SUBBRAND_NAME_EN()+"', "
		    						+ "PRODUCT_MODEL_CODE = '"+req.getPRODUCT_MODEL_CODE()+"', "
		    						+ "PRODUCT_MODEL_NAME_TH = '"+req.getPRODUCT_MODEL_NAME_TH()+"', "
		    						+ "PRODUCT_MODEL_NAME_EN = '"+req.getPRODUCT_MODEL_NAME_EN()+"', "
		    						+ "IS_TAX_VALUE = '"+req.getIS_TAX_VALUE()+"', "
		    						+ "TAX_VALUE = '"+req.getTAX_VALUE()+"', "
		    						+ "IS_TAX_VOLUMN = '"+req.getIS_TAX_VOLUMN()+"', "
		    						+ "TAX_VOLUMN = '"+req.getTAX_VOLUMN()+"', "
		    						+ "TAX_VOLUMN_UNIT = '"+req.getTAX_VOLUMN_UNIT()+"', "
		    						+ "LICENSE_PLATE = '"+req.getLICENSE_PLATE()+"', "
		    						+ "ENGINE_NO = '"+req.getENGINE_NO()+"', "
		    						+ "CHASSIS_NO = '"+req.getCHASSIS_NO()+"', "
		    						+ "PRODUCT_DESC = '"+req.getPRODUCT_DESC()+"', "
		    						+ "SUGAR = '"+req.getSUGAR()+"', "
		    						+ "CO2 = '"+req.getCO2()+"', "
		    						+ "DEGREE = '"+req.getDEGREE()+"', "
		    						+ "PRICE = '"+req.getPRICE()+"', "
		    						+ "SIZES = '"+req.getSIZES()+"', "
		    						+ "SIZES_UNIT = '"+req.getSIZES_UNIT()+"', "
		    						+ "QUANTITY = '"+req.getQUANTITY()+"', "
		    						+ "QUANTITY_UNIT = '"+req.getQUANTITY_UNIT()+"', "
		    						+ "VOLUMN = '"+req.getVOLUMN()+"', "
		    						+ "VOLUMN_UNIT = '"+req.getVOLUMN_UNIT()+"', "
		    						+ "REMARK = '"+req.getREMARK()+"', "
		    						+ "IS_DOMESTIC = '"+req.getIS_DOMESTIC()+"', "
		    						+ "IS_ILLEGAL = '"+req.getIS_ILLEGAL()+"', "
		    						+ "IS_ACTIVE = '"+req.getIS_ACTIVE()+"' "
		    						+ "WHERE PRODUCT_ID   = '"+req.getPRODUCT_ID()+"' ");
		    				log.info("[SQL] : "+sqlBuilderSub.toString());
		    				
		    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
		    		
		    		
		    		if(req.getArrestProductMapping().size() > 0){
				    	
				    	for(ArrestProductMapping item : req.getArrestProductMapping()){
				    	
				    		StringBuilder sqlBuilderSub1 = new StringBuilder()
				    				.append("UPDATE OPS_ARREST_PRODUCT_MAPPING "
				    						+ "SET "
				    						+ "PRODUCT_ID = '"+item.getPRODUCT_ID()+"', "
				    						+ "PRODUCT_MAPPING_REF_ID = '"+item.getPRODUCT_MAPPING_REF_ID()+"', "
				    						+ "PRODUCT_CODE = '"+item.getPRODUCT_CODE()+"', "
				    						+ "PRODUCT_REF_CODE = '"+item.getPRODUCT_REF_CODE()+"', "
				    						+ "PRODUCT_GROUP_ID = '"+item.getPRODUCT_GROUP_ID()+"', "
				    						+ "PRODUCT_CATEGORY_ID = '"+item.getPRODUCT_CATEGORY_ID()+"', "
				    						+ "PRODUCT_TYPE_ID = '"+item.getPRODUCT_TYPE_ID()+"', "
				    						+ "PRODUCT_SUBTYPE_ID = '"+item.getPRODUCT_SUBTYPE_ID()+"', "
				    						+ "PRODUCT_SUBSETTYPE_ID = '"+item.getPRODUCT_SUBSETTYPE_ID()+"', "
				    						+ "PRODUCT_BRAND_ID = '"+item.getPRODUCT_BRAND_ID()+"', "
				    						+ "PRODUCT_SUBBRAND_ID = '"+item.getPRODUCT_SUBBRAND_ID()+"', "
				    						+ "PRODUCT_MODEL_ID = '"+item.getPRODUCT_MODEL_ID()+"', "
				    						+ "PRODUCT_TAXDETAIL_ID = '"+item.getPRODUCT_TAXDETAIL_ID()+"', "
				    						+ "SIZES_UNIT_ID = '"+item.getSIZES_UNIT_ID()+"', "
				    						+ "QUATITY_UNIT_ID = '"+item.getQUATITY_UNIT_ID()+"', "
				    						+ "VOLUMN_UNIT_ID = '"+item.getVOLUMN_UNIT_ID()+"', "
				    						+ "PRODUCT_GROUP_CODE = '"+item.getPRODUCT_GROUP_CODE()+"', "
				    						+ "PRODUCT_GROUP_NAME = '"+item.getPRODUCT_GROUP_NAME()+"', "
				    						+ "PRODUCT_CATEGORY_CODE = '"+item.getPRODUCT_CATEGORY_CODE()+"', "
				    						+ "PRODUCT_CATEGORY_NAME = '"+item.getPRODUCT_CATEGORY_NAME()+"', "
				    						+ "PRODUCT_TYPE_CODE = '"+item.getPRODUCT_TYPE_CODE()+"', "
				    						+ "PRODUCT_TYPE_NAME = '"+item.getPRODUCT_TYPE_NAME()+"', "
				    						+ "PRODUCT_SUBTYPE_CODE = '"+item.getPRODUCT_SUBTYPE_CODE()+"', "
				    						+ "PRODUCT_SUBTYPE_NAME = '"+item.getPRODUCT_SUBTYPE_NAME()+"', "
				    						+ "PRODUCT_SUBSETTYPE_CODE = '"+item.getPRODUCT_SUBSETTYPE_CODE()+"', "
				    						+ "PRODUCT_SUBSETTYPE_NAME = '"+item.getPRODUCT_SUBSETTYPE_NAME()+"', "
				    						+ "PRODUCT_BRAND_CODE = '"+item.getPRODUCT_BRAND_CODE()+"', "
				    						+ "PRODUCT_BRAND_NAME_TH = '"+item.getPRODUCT_BRAND_NAME_TH()+"', "
				    						+ "PRODUCT_BRAND_NAME_EN = '"+item.getPRODUCT_BRAND_NAME_EN()+"', "
				    						+ "PRODUCT_SUBBRAND_CODE = '"+item.getPRODUCT_SUBBRAND_CODE()+"', "
				    						+ "PRODUCT_SUBBRAND_NAME_TH = '"+item.getPRODUCT_SUBBRAND_NAME_TH()+"', "
				    						+ "PRODUCT_SUBBRAND_NAME_EN = '"+item.getPRODUCT_SUBBRAND_NAME_EN()+"', "
				    						+ "PRODUCT_MODEL_CODE = '"+item.getPRODUCT_MODEL_CODE()+"', "
				    						+ "PRODUCT_MODEL_NAME_TH = '"+item.getPRODUCT_MODEL_NAME_TH()+"', "
				    						+ "PRODUCT_MODEL_NAME_EN = '"+item.getPRODUCT_MODEL_NAME_EN()+"', "
				    						+ "IS_TAX_VALUE = '"+item.getIS_TAX_VALUE()+"', "
				    						+ "TAX_VALUE = '"+item.getTAX_VALUE()+"', "
				    						+ "IS_TAX_VOLUMN = '"+item.getIS_TAX_VOLUMN()+"', "
				    						+ "TAX_VOLUMN = '"+item.getTAX_VOLUMN()+"', "
				    						+ "TAX_VOLUMN_UNIT = '"+item.getTAX_VOLUMN_UNIT()+"', "
				    						+ "LICENSE_PLATE = '"+item.getLICENSE_PLATE()+"', "
				    						+ "ENGINE_NO = '"+item.getENGINE_NO()+"', "
				    						+ "CHASSIS_NO = '"+item.getCHASSIS_NO()+"', "
				    						+ "PRODUCT_DESC = '"+item.getPRODUCT_DESC()+"', "
				    						+ "SUGAR = '"+item.getSUGAR()+"', "
				    						+ "CO2 = '"+item.getCO2()+"', "
				    						+ "DEGREE = '"+item.getDEGREE()+"', "
				    						+ "PRICE = '"+item.getPRICE()+"', "
				    						+ "SIZES = '"+item.getSIZES()+"', "
				    						+ "SIZES_UNIT = '"+item.getSIZES_UNIT()+"', "
				    						+ "QUANTITY = '"+item.getQUANTITY()+"', "
				    						+ "QUANTITY_UNIT = '"+item.getQUANTITY_UNIT()+"', "
				    						+ "VOLUMN = '"+item.getVOLUMN()+"', "
				    						+ "VOLUMN_UNIT = '"+item.getVOLUMN_UNIT()+"', "
				    						+ "REMARK = '"+item.getREMARK()+"', "
				    						+ "PRODUCT_RESULT = '"+item.getPRODUCT_RESULT()+"', "
				    						+ "SCIENCE_RESULT_DESC = '"+item.getSCIENCE_RESULT_DESC()+"', "
				    						+ "VAT = '"+item.getVAT()+"', "
				    						+ "IS_DOMESTIC = '"+item.getIS_DOMESTIC()+"', "
				    						+ "IS_ILLEGAL = '"+item.getIS_ILLEGAL()+"', "
				    						+ "IS_SCIENCE = '"+item.getIS_SCIENCE()+"', "
				    						+ "IS_ACTIVE = '"+item.getIS_ACTIVE()+"' "
				    						+ "WHERE PRODUCT_MAPPING_ID = '"+req.getPRODUCT_MAPPING_ID()+"' ");
				    				log.info("[SQL] : "+sqlBuilderSub1.toString());
				    				
				    		getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[] {});
				    		
				    		
				    	}
				    }
		    		
		    	}
		    }
	    }
		
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean ArrestProductupdDelete(List<ArrestProductupdDeleteReq> req) {

		if(req != null) {
	    	log.info("[Sub] Size : "+req.size());
	    	
		    if(req.size() > 0){
		    	
		    	for(ArrestProductupdDeleteReq item : req){
		    		
		    		StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_ARREST_PRODUCT SET IS_ACTIVE = '0' WHERE PRODUCT_ID = '"+item.getPRODUCT_ID()+"' ");
		    		StringBuilder sqlBuilder2 = new StringBuilder().append("UPDATE OPS_ARREST_PRODUCT_MAPPING SET IS_ACTIVE = '0' WHERE PRODUCT_ID = '"+item.getPRODUCT_ID()+"' ");
		    		StringBuilder sqlBuilder3 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT_PRODUCT SET IS_ACTIVE = '0' WHERE PRODUCT_ID = '"+item.getPRODUCT_ID()+"' ");
		    		
		    		
		    		getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
		    		getJdbcTemplate().update(sqlBuilder2.toString(), new Object[] {});
		    		getJdbcTemplate().update(sqlBuilder3.toString(), new Object[] {});
		    		
		    	}
		    }
	    }
		
		return true;
	}
}
