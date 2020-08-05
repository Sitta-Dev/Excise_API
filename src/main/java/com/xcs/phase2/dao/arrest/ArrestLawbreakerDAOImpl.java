package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.arrest.ArrestLawbreaker;
import com.xcs.phase2.request.arrest.ArrestLawbreakerupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestLawbreakerResponse;
import com.xcs.phase2.response.arrest.ArrestLawbreakerinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
@Transactional
public class ArrestLawbreakerDAOImpl extends ArrestExt implements ArrestLawbreakerDAO{

	private static final Logger log = LoggerFactory.getLogger(ArrestLawbreakerDAOImpl.class);

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ArrestLawbreakerinsAllResponse ArrestLawbreakerinsAll(List<ArrestLawbreaker> req) {
		
		ArrestLawbreakerinsAllResponse res = new ArrestLawbreakerinsAllResponse();
		
		try {
			
			if(req != null) {
		    	log.info("[Sub] Size : "+req.size());
		    	List<ArrestLawbreakerResponse> list = new ArrayList<ArrestLawbreakerResponse>();
			    if(req.size() > 0){
			    	
			    	for(ArrestLawbreaker item : req){
			    		
			    		String LAWBREAKER_ID = getSequences("SELECT \"OPS_ARREST_LAWBREAKER_SEQ\".NEXTVAL FROM DUAL");
			    		ArrestLawbreakerResponse  obj = new ArrestLawbreakerResponse();
			    		obj.setLAWBREAKER_ID(Integer.parseInt(LAWBREAKER_ID));
						obj.setPERSON_ID(item.getPERSON_ID());

			    		StringBuilder sqlBuilderSub = new StringBuilder()
			    			    .append("Insert into OPS_ARREST_LAWBREAKER (" +
			    			    		"LAWBREAKER_ID," +
			    			    		"ARREST_ID," +
			    			    		"PERSON_ID," +
			    			    		"TITLE_ID," +
			    			    		"PERSON_TYPE," +
			    			    		"ENTITY_TYPE," +
			    			    		"TITLE_NAME_TH," +
			    			    		"TITLE_NAME_EN," +
			    			    		"TITLE_SHORT_NAME_TH," +
			    			    		"TITLE_SHORT_NAME_EN," +
			    			    		"FIRST_NAME," +
			    			    		"MIDDLE_NAME," +
			    			    		"LAST_NAME," +
			    			    		"OTHER_NAME," +
			    			    		"COMPANY_REGISTRATION_NO," +
			    			    		"EXCISE_REGISTRATION_NO," +
			    			    		"ID_CARD," +
			    			    		"AGE," +
			    			    		"PASSPORT_NO," +
			    			    		"CAREER," +
			    			    		"PERSON_DESC," +
			    			    		"EMAIL," +
			    			    		"TEL_NO," +
			    			    		"MISTREAT_NO," +
										"COMPANY_NAME," +
			    			    		"IS_ACTIVE) " +
			    			    		"values (" +
			    			    		"'"+LAWBREAKER_ID+"'," +
					    	    		"'"+item.getARREST_ID()+"'," +
					    	    		"'"+item.getPERSON_ID()+"'," +
			    			    		"'"+item.getTITLE_ID()+"'," +
			    			    		"'"+item.getPERSON_TYPE()+"'," +
			    			    		"'"+item.getENTITY_TYPE()+"'," +
			    			    		"'"+item.getTITLE_NAME_TH()+"'," +
			    			    		"'"+item.getTITLE_NAME_EN()+"'," +
			    			    		"'"+item.getTITLE_SHORT_NAME_TH()+"'," +
			    			    		"'"+item.getTITLE_SHORT_NAME_EN()+"'," +
			    			    		"'"+item.getFIRST_NAME()+"'," +
			    			    		"'"+item.getMIDDLE_NAME()+"'," +
			    			    		"'"+item.getLAST_NAME()+"'," +
			    			    		"'"+item.getOTHER_NAME()+"'," +
			    			    		"'"+item.getCOMPANY_REGISTRATION_NO()+"'," +
			    			    		"'"+item.getEXCISE_REGISTRATION_NO()+"'," +
			    			    		"'"+item.getID_CARD()+"'," +
			    			    		"'"+item.getAGE()+"'," +
			    			    		"'"+item.getPASSPORT_NO()+"'," +
			    			    		"'"+item.getCAREER()+"'," +
			    			    		"'"+item.getPERSON_DESC()+"'," +
			    			    		"'"+item.getEMAIL()+"'," +
			    			    		"'"+item.getTEL_NO()+"'," +
			    			    		"'"+item.getMISTREAT_NO()+"'," +
										"'"+item.getCOMPANY_NAME()+"'," +
			    			    		"'"+item.getIS_ACTIVE()+"' )");
			    				log.info("[SQL] : "+sqlBuilderSub.toString());
			    				
			    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
					    	    
					    	    list.add(obj);
			    	}
			    }
			    res.setArrestLawbreaker(list);
		    }
			
			res.setIsSuccess(Message.TRUE);
		    res.setMsg(Message.COMPLETE);
		    
		    return res;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setIsSuccess(Message.FALSE);
		    res.setMsg(e.getMessage());
		    res.setArrestLawbreaker(null);
			return res;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean ArrestLawbreakerupdDelete(List<ArrestLawbreakerupdDeleteReq> req) {

		if(req != null) {
	    	log.info("[Sub] Size : "+req.size());
	    	
		    if(req.size() > 0){
		    	
		    	for(ArrestLawbreakerupdDeleteReq item : req){
		    		
		    		
		    		StringBuilder sqlBuilder = new StringBuilder().append("UPDATE OPS_ARREST_LAWBREAKER SET IS_ACTIVE = '0' WHERE LAWBREAKER_ID = '"+item.getLAWBREAKER_ID()+"' ");
		    		StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_ARREST_INDICTMENT_DETAIL SET IS_ACTIVE = '0' WHERE LAWBREAKER_ID = '"+item.getLAWBREAKER_ID()+"' ");
		    		
		    		
		    		getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		    		getJdbcTemplate().update(sqlBuilder1.toString(), new Object[] {});
		    		
		    	}
		    }
	    }
		
		return true;
	}
}
