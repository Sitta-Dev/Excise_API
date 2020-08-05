package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.lawsult.LawsuitPayment;
import com.xcs.phase2.model.lawsult.LawsuitPaymentDetail;
import com.xcs.phase2.request.lawsult.LawsuitPaymentgetByConReq;
import com.xcs.phase2.request.lawsult.LawsuitPaymentupdDeleteReq;
import com.xcs.phase2.response.lawsult.LawsuitPaymentDetailResponse;
import com.xcs.phase2.response.lawsult.LawsuitPaymentResponse;
import com.xcs.phase2.response.lawsult.LawsuitPaymentinsAllResposne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class LawsuitPaymentDAOImpl extends LawsultExt implements LawsuitPaymentDAO{

	private static final Logger log = LoggerFactory.getLogger(LawsuitPaymentDAOImpl.class);

	@Override
	public LawsuitPaymentinsAllResposne LawsuitPaymentinsAll(List<LawsuitPayment> req) {
		
		LawsuitPaymentinsAllResposne res = new LawsuitPaymentinsAllResposne();
		
		try {
			
			
			if(req != null) {
		    	log.info("[Sub] Size : "+req.size());
		    	List<LawsuitPaymentResponse> list = new ArrayList<LawsuitPaymentResponse>();
			    if(req.size() > 0){
			    	
			    	for(LawsuitPayment item : req){
			    		
			    		String PAYMENT_ID = getSequences("SELECT \"OPS_PAYMENT_SEQ\".NEXTVAL FROM DUAL");
			    		LawsuitPaymentResponse  obj = new LawsuitPaymentResponse();
			    		obj.setPAYMENT_ID(Integer.parseInt(PAYMENT_ID));
			    		
			    		StringBuilder sqlBuilderSub = new StringBuilder()
			    			    .append("Insert into OPS_PAYMENT ( " +
			    			    		"PAYMENT_ID," +
			    			    		"LAWSUIT_DETAIL_ID," +
			    			    		"COMPARE_DETAIL_ID," +
			    			    		"FINE_TYPE," +
			    			    		"FINE," +
			    			    		"PAYMENT_PERIOD_NO," +
			    			    		"PAYMENT_DATE," +
			    			    		"IS_REQUEST_REWARD," +
										"PAYMENT_CHANNEL," +
										"PAYMENT_BANK," +
										"PAYMENT_REF_NO," +
										"IS_REVENUE," +
			    			    		"IS_ACTIVE " +
			    			    		" ) values (" +
			    			    		"'"+PAYMENT_ID+"'," +
			    			    		"'"+item.getLAWSUIT_DETAIL_ID()+"'," +
			    			    		"'"+item.getCOMPARE_DETAIL_ID()+"'," +
			    			    		"'"+item.getFINE_TYPE()+"'," +
			    			    		"'"+item.getFINE()+"'," +
			    			    		"'"+item.getPAYMENT_PERIOD_NO()+"'," +
			    			    		"TO_TIMESTAMP_TZ('"+item.getPAYMENT_DATE()+"', '"+ Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), " +
			    			    		"'"+item.getIS_REQUEST_REWARD()+"'," +
										"'"+item.getPAYMENT_CHANNEL()+"'," +
										"'"+item.getPAYMENT_BANK()+"'," +
										"'"+item.getPAYMENT_REF_NO()+"'," +
										"'"+item.getIS_REVENUE()+"'," +
			    			    		"'"+item.getIS_ACTIVE()+"'" +
			    			    		" )");
			    				log.info("[SQL] : "+sqlBuilderSub.toString());
					    	    getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
					    	    
					    	    
					    	    if(item.getLawsuitPaymentDetail() != null) {
							    	log.info("[Sub] Size : "+item.getLawsuitPaymentDetail().size());
							    	List<LawsuitPaymentDetailResponse> list1 = new ArrayList<LawsuitPaymentDetailResponse>();
							    	
								    if(item.getLawsuitPaymentDetail().size() > 0){
								    	for(LawsuitPaymentDetail item1 : item.getLawsuitPaymentDetail()){
								    		
								    		String PAYMENT_DETAIL_ID = getSequences("SELECT \"OPS_PAYMENT_DETAIL_SEQ\".NEXTVAL FROM DUAL");
								    		LawsuitPaymentDetailResponse  obj1 = new LawsuitPaymentDetailResponse();
								    		obj1.setPAYMENT_DETAIL_ID(Integer.parseInt(PAYMENT_DETAIL_ID));

								    		StringBuilder sqlBuilderSub1 = new StringBuilder()
								    			    .append("Insert into OPS_PAYMENT_DETAIL ( " +
								    			    		"PAYMENT_DETAIL_ID," +
								    			    		"PAYMENT_ID," +
								    			    		"NOTICE_ID," +
								    			    		"IS_REQUEST_BRIBE," +
								    			    		"IS_ACTIVE " +
								    			    		" ) values (" +
								    			    		"'"+PAYMENT_DETAIL_ID+"'," +
								    			    		"'"+PAYMENT_ID+"'," +
								    			    		"'"+item1.getNOTICE_ID()+"'," +
								    			    		"'"+item1.getIS_REQUEST_BRIBE()+"'," +
								    			    		"'"+item1.getIS_ACTIVE()+"'" +
								    			    		" )");
								    				log.info("[SQL] : "+sqlBuilderSub1.toString());
								    				
								    		getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[] {});
								    		list1.add(obj1);
								    	}
								    }
								    obj.setLawsuitPaymentDetail(list1);
							    }
					    	    
					    	    list.add(obj);
			    	}
			    }
			    res.setLawsuitPayment(list);
		    }
			
			res.setIsSuccess(Message.TRUE);
		    res.setMsg(Message.COMPLETE);
		    
		    return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setIsSuccess(Message.FALSE);
		    res.setMsg(e.getMessage());
		    res.setLawsuitPayment(null);
			return res;
		}
	}

	@Override
	public Boolean LawsuitPaymentupdDelete(List<LawsuitPaymentupdDeleteReq> req) {

		if(req != null) {
	    	log.info("[Sub] Size : "+req.size());
	    	
		    if(req.size() > 0){
		    	
		    	for(LawsuitPaymentupdDeleteReq item : req){
		    		
		    		StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_PAYMENT SET IS_ACTIVE = '0' WHERE PAYMENT_ID = '"+item.getPAYMENT_ID()+"' ");
		    		StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_PAYMENT_DETAIL SET IS_ACTIVE = '0' WHERE PAYMENT_ID = '"+item.getPAYMENT_ID()+"' ");
		    		log.info("[SQL] Sub : "+sqlBuilder.toString());
		    		getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		    		getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
		    		
		    	}
		    }
	    }
		
		return true;
	}

	@Override
	public List<LawsuitPayment> LawsuitPaymentgetByCon(LawsuitPaymentgetByConReq req) {

		StringBuilder sqlBuilder = new StringBuilder()
				.append("    SELECT DISTINCT" +
						"    OPS_PAYMENT.*" +
						"    FROM OPS_PAYMENT" +
						"    INNER JOIN OPS_LAWSUIT_DETAIL on OPS_PAYMENT.LAWSUIT_DETAIL_ID = OPS_LAWSUIT_DETAIL.LAWSUIT_DETAIL_ID" +
						"    INNER JOIN OPS_LAWSUIT on OPS_LAWSUIT_DETAIL.LAWSUIT_ID = OPS_LAWSUIT.LAWSUIT_ID" +
						"    INNER JOIN OPS_ARREST_INDICTMENT_DETAIL on OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT_DETAIL.INDICTMENT_ID" +
						"    INNER JOIN OPS_ARREST_LAWBREAKER on OPS_ARREST_INDICTMENT_DETAIL.LAWBREAKER_ID = OPS_ARREST_LAWBREAKER.LAWBREAKER_ID" +
						"    WHERE OPS_PAYMENT.IS_ACTIVE = 1" +
						"    AND OPS_PAYMENT.LAWSUIT_DETAIL_ID = '"+req.getLAWSUIT_DETAIL_ID()+"' ");

		log.info("[SQL]  : " + sqlBuilder.toString());

		@SuppressWarnings("unchecked")
		List<LawsuitPayment> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

			public LawsuitPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
				LawsuitPayment item = new LawsuitPayment();
				item.setPAYMENT_ID(rs.getInt("PAYMENT_ID"));
				item.setLAWSUIT_DETAIL_ID(rs.getInt("LAWSUIT_DETAIL_ID"));
				item.setCOMPARE_DETAIL_ID(rs.getInt("COMPARE_DETAIL_ID"));
				item.setFINE_TYPE(rs.getInt("FINE_TYPE"));
				item.setFINE(rs.getFloat("FINE"));
				item.setPAYMENT_PERIOD_NO(rs.getInt("PAYMENT_PERIOD_NO"));
				item.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
				item.setIS_REQUEST_REWARD(rs.getInt("IS_REQUEST_REWARD"));
				item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));

				item.setPAYMENT_CHANNEL(rs.getInt("PAYMENT_CHANNEL"));
				item.setPAYMENT_BANK(rs.getString("PAYMENT_BANK"));
				item.setPAYMENT_REF_NO(rs.getString("PAYMENT_REF_NO"));
				item.setIS_REVENUE(rs.getInt("IS_REVENUE"));


				item.setLawsuitPaymentDetail(getLawsuitPaymentDetail(rs.getInt("PAYMENT_ID")));
				return item;
			}
		});

		return dataList;

	}


}
