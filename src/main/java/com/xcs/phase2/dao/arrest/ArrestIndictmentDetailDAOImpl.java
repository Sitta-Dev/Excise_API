package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.arrest.ArrestIndictmentDetail;
import com.xcs.phase2.request.arrest.ArrestIndictmentDetailupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestIndictmentDetailResponse;
import com.xcs.phase2.response.arrest.ArrestIndictmentDetailinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
@Transactional
public class ArrestIndictmentDetailDAOImpl extends ArrestExt implements ArrestIndictmentDetailDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestIndictmentDetailDAOImpl.class);

	@Override
    @Transactional(rollbackFor = Exception.class)
	public ArrestIndictmentDetailinsAllResponse ArrestIndictmentDetailinsAll(List<ArrestIndictmentDetail> req) {
		
		ArrestIndictmentDetailinsAllResponse res = new ArrestIndictmentDetailinsAllResponse();
		
		try {
			
			
			if(req != null) {
		    	log.info("[Sub] Size : "+req.size());
		    	List<ArrestIndictmentDetailResponse> list = new ArrayList<ArrestIndictmentDetailResponse>();
			    if(req.size() > 0){
			    	
			    	for(ArrestIndictmentDetail item1 : req){
			    		
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
					    	    		"'"+item1.getINDICTMENT_ID()+"'," +
					    	    		"'"+item1.getLAWBREAKER_ID()+"'," +
										"'"+item1.getFINE_ESTIMATE()+"'," + //
					    	    		"'"+item1.getIS_ACTIVE()+"')");
			    				log.info("[SQL] : "+sqlBuilderSub1.toString());
			    				
			    		getJdbcTemplate().update(sqlBuilderSub1.toString(), new Object[] {});
					    	    
					    	    list.add(obj1);
			    	}
			    }
			    res.setArrestIndictmentDetail(list);
		    }
			
			res.setIsSuccess(Message.TRUE);
		    res.setMsg(Message.COMPLETE);
		    
		    return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setIsSuccess(Message.FALSE);
		    res.setMsg(e.getMessage());
		    res.setArrestIndictmentDetail(null);
			return res;
		}
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	public Boolean ArrestIndictmentDetailupdDelete(List<ArrestIndictmentDetailupdDeleteReq> req) {

		if(req != null) {
	    	log.info("[Sub] Size : "+req.size());
	    	
		    if(req.size() > 0){
		    	
		    	for(ArrestIndictmentDetailupdDeleteReq item : req){
		    		
		    		StringBuilder sqlBuilder9 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT_DETAIL SET IS_ACTIVE = '0' WHERE INDICTMENT_DETAIL_ID = '"+item.getINDICTMENT_DETAIL_ID()+"' ");
		    		
		    		getJdbcTemplate().update(sqlBuilder9.toString(), new Object[] {});
		    		
		    	}
		    }
	    }
		
		return true;
	}
}
