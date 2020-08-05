package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.arrest.ArrestIndictment;
import com.xcs.phase2.model.arrest.ArrestIndictmentDetail;
import com.xcs.phase2.model.arrest.ArrestIndictmentProduct;
import com.xcs.phase2.request.arrest.ArrestIndictmentupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestIndictmentDetailResponse;
import com.xcs.phase2.response.arrest.ArrestIndictmentProductResponse;
import com.xcs.phase2.response.arrest.ArrestIndictmentResponse;
import com.xcs.phase2.response.arrest.ArrestIndictmentinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ArrestIndictmentDAOImpl extends ArrestExt implements ArrestIndictmentDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestIndictmentDAOImpl.class);

	@Override
    @Transactional(rollbackFor = Exception.class)
	public ArrestIndictmentinsAllResponse ArrestIndictmentinsAll(List<ArrestIndictment> req) {
		
		ArrestIndictmentinsAllResponse res = new ArrestIndictmentinsAllResponse();
		
		try {
			
			if(req != null) {
		    	log.info("[Sub] Size : "+req.size());
		    	List<ArrestIndictmentResponse> list = new ArrayList<ArrestIndictmentResponse>();
			    if(req.size() > 0){
			    	
			    	for(ArrestIndictment item : req){
			    		
			    		String INDICTMENT_ID = getSequences("SELECT \"OPS_ARREST_INDICTMENT_SEQ\".NEXTVAL FROM DUAL");
			    		ArrestIndictmentResponse  obj = new ArrestIndictmentResponse();
			    		obj.setINDICTMENT_ID(Integer.parseInt(INDICTMENT_ID));
			    		
			    		StringBuilder sqlBuilderSub = new StringBuilder()
					    	    .append("Insert into OPS_ARREST_INDICTMENT (" +
					    	    		"INDICTMENT_ID," +
					    	    		"ARREST_ID," +
					    	    		"GUILTBASE_ID," +
					    	    		"FINE_ESTIMATE," +
					    	    		"IS_LAWSUIT_COMPLETE," +
					    	    		"IS_ACTIVE) values (" +
					    	    		"'"+INDICTMENT_ID+"'," +
					    	    		"'"+item.getARREST_ID()+"'," +
					    	    		"'"+item.getGUILTBASE_ID()+"'," +
					    	    		"'"+item.getFINE_ESTIMATE()+"'," +
					    	    		"'"+item.getIS_LAWSUIT_COMPLETE()+"'," +
					    	    		"'"+item.getIS_ACTIVE()+"')");
					    		log.info("[SQL] Sub : "+sqlBuilderSub.toString());
					    	    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});

					    	    if(item.getArrestIndictmentDetail() != null) {
							    	log.info("[Sub] Size : "+item.getArrestIndictmentDetail().size());
							    	List<ArrestIndictmentDetailResponse> list1 = new ArrayList<ArrestIndictmentDetailResponse>();
							    	
								    if(item.getArrestIndictmentDetail().size() > 0){
								    	for(ArrestIndictmentDetail item1 : item.getArrestIndictmentDetail()){
								    		
								    		String INDICTMENT_DETAIL_ID = getSequences("SELECT \"OPS_ARREST_INDICTMENT_DETAIL_SEQ\".NEXTVAL FROM DUAL");
								    		ArrestIndictmentDetailResponse  obj1 = new ArrestIndictmentDetailResponse();
								    		obj1.setINDICTMENT_DETAIL_ID(Integer.parseInt(INDICTMENT_DETAIL_ID));

								    		
								    		StringBuilder sqlBuilderSub1 = new StringBuilder()
								    				.append("Insert into OPS_ARREST_INDICTMENT_DETAIL (" +
										    	    		"INDICTMENT_DETAIL_ID," +
										    	    		"INDICTMENT_ID," +
										    	    		"LAWBREAKER_ID," +
															"FINE_ESTIMATE," +
										    	    		"IS_ACTIVE) values (" +
										    	    		"'"+INDICTMENT_DETAIL_ID+"'," +
										    	    		"'"+INDICTMENT_ID+"'," +
										    	    		"'"+item1.getLAWBREAKER_ID()+"'," +
															"'"+item1.getFINE_ESTIMATE()+"'," + //
										    	    		"'"+item1.getIS_ACTIVE()+"')");
								    				log.info("[SQL] : "+sqlBuilderSub1.toString());
								    				
								    		getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[] {});
								    		list1.add(obj1);

											if(item1.getArrestIndictmentProduct() != null) {
												log.info("[Sub] Size : "+item1.getArrestIndictmentProduct().size());
												List<ArrestIndictmentProductResponse> list2 = new ArrayList<ArrestIndictmentProductResponse>();

												if(item1.getArrestIndictmentProduct().size() > 0){
													for(ArrestIndictmentProduct item2 : item1.getArrestIndictmentProduct()){

														String PRODUCT_INDICTMENT_ID = getSequences("SELECT \"OPS_ARREST_INDICTMENT_PRODUCT_SEQ\".NEXTVAL FROM DUAL");
														ArrestIndictmentProductResponse  obj2 = new ArrestIndictmentProductResponse();
														obj2.setPRODUCT_INDICTMENT_ID(Integer.parseInt(PRODUCT_INDICTMENT_ID));

														StringBuilder sqlBuilderSub2 = new StringBuilder()
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
																		"'"+item2.getPRODUCT_ID()+"'," +
																		"'"+INDICTMENT_DETAIL_ID+"'," +
																		"'"+item2.getSIZES_UNIT_ID()+"'," +
																		"'"+item2.getQUATITY_UNIT_ID()+"'," +
																		"'"+item2.getVOLUMN_UNIT_ID()+"'," +
																		"'"+item2.getSIZES()+"'," +
																		"'"+item2.getSIZES_UNIT()+"'," +
																		"'"+item2.getQUANTITY()+"'," +
																		"'"+item2.getQUANTITY_UNIT()+"'," +
																		"'"+item2.getVOLUMN()+"'," +
																		"'"+item2.getVOLUMN_UNIT()+"'," +
																		"'"+item2.getFINE_ESTIMATE()+"'," +
																		"'"+item2.getIS_ILLEGAL()+"'," +
																		"'"+item2.getIS_ACTIVE()+"')");
														log.info("[SQL] : "+sqlBuilderSub1.toString());

														getJdbcTemplate().update(sqlBuilderSub2.toString(), new Object[] {});
														list2.add(obj2);
													}
												}
                                                obj1.setArrestIndictmentProduct(list2);
											}
								    	}
								    }
								    obj.setArrestIndictmentDetail(list1);
							    }
					    	    
					    	    list.add(obj);
			    	}
			    }
			    res.setArrestIndictment(list);
		    }
			
			res.setIsSuccess(Message.TRUE);
		    res.setMsg(Message.COMPLETE);
		    
		    return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setIsSuccess(Message.FALSE);
		    res.setMsg(e.getMessage());
		    res.setArrestIndictment(null);
			return res;
		}
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	public Boolean ArrestIndictmentupdByCon(List<ArrestIndictment> request) {

		if(request != null) {
	    	log.info("[Sub] Size : "+request.size());
	    	
		    if(request.size() > 0){

		    	for(ArrestIndictment req : request){
		    		StringBuilder sqlBuilderSub = new StringBuilder()
		    				.append("UPDATE OPS_ARREST_INDICTMENT "
		    						+ "SET "
		    						+"ARREST_ID = '"+req.getARREST_ID()+"',"
		    						+"GUILTBASE_ID = '"+req.getGUILTBASE_ID()+"',"
		    						+"FINE_ESTIMATE = '"+req.getFINE_ESTIMATE()+"',"
		    						+"IS_LAWSUIT_COMPLETE = '"+req.getIS_LAWSUIT_COMPLETE()+"',"
		    						+"IS_ACTIVE = '"+req.getIS_ACTIVE()+"' "
		    						+ "WHERE INDICTMENT_ID = '"+req.getINDICTMENT_ID()+"' ");
		    				log.info("[SQL] : "+sqlBuilderSub.toString());

		    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});

		    		if(req.getArrestIndictmentDetail() != null){

						for(ArrestIndictmentDetail req1 : req.getArrestIndictmentDetail()){
							StringBuilder sqlBuilderSub1 = new StringBuilder()
									.append("UPDATE OPS_ARREST_INDICTMENT_DETAIL "
											+"SET "
											+"INDICTMENT_ID = '"+req1.getINDICTMENT_ID()+"',"
											+"LAWBREAKER_ID = '"+req1.getLAWBREAKER_ID()+"',"
											+"FINE_ESTIMATE = '"+req1.getFINE_ESTIMATE()+"',"
											+"IS_ACTIVE = '"+req1.getIS_ACTIVE()+"' "
											+ "WHERE INDICTMENT_DETAIL_ID = '"+req1.getINDICTMENT_DETAIL_ID()+"' ");
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
	public Boolean ArrestIndictmentupdDelete(List<ArrestIndictmentupdDeleteReq> req) {

		if(req != null) {
	    	log.info("[Sub] Size : "+req.size());
	    	
		    if(req.size() > 0){
		    	
		    	for(ArrestIndictmentupdDeleteReq item : req){
		    		
		    		StringBuilder sqlBuilder7 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT SET IS_ACTIVE = '0' WHERE INDICTMENT_ID = '"+item.getINDICTMENT_ID()+"' ");
		    		StringBuilder sqlBuilder9 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT_DETAIL SET IS_ACTIVE = '0' WHERE INDICTMENT_ID = '"+item.getINDICTMENT_ID()+"' ");

                    StringBuilder sqlBuilder8 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT_PRODUCT SET IS_ACTIVE = '0' WHERE INDICTMENT_ID in(select INDICTMENT_DETAIL_ID from OPS_ARREST_INDICTMENT_DETAIL where INDICTMENT_ID = '"+item.getINDICTMENT_ID()+"') ");
		    		
		    		getJdbcTemplate().update(sqlBuilder7.toString(), new Object[] {});
		    		getJdbcTemplate().update(sqlBuilder8.toString(), new Object[] {});
		    		getJdbcTemplate().update(sqlBuilder9.toString(), new Object[] {});
		    		
		    	}
		    }
	    }
		
		return true;
	}
}
