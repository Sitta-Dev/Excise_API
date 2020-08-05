package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.request.lawsult.LawsuitMistreatNoDeleteReq;
import com.xcs.phase2.request.lawsult.LawsuitMistreatNoUpdateReq;
import com.xcs.phase2.response.lawsult.LawsuitMistreatNoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LawsuitMistreatNoDAOImpl extends LawsultExt implements LawsuitMistreatNoDAO{

	private static final Logger log = LoggerFactory.getLogger(LawsuitMistreatNoDAOImpl.class);

	@Override
	public LawsuitMistreatNoResponse LawsuitMistreatNoUpdate(LawsuitMistreatNoUpdateReq req) {
		
		LawsuitMistreatNoResponse response = new LawsuitMistreatNoResponse();
		
		try {
			
			String MISTREAT_NO = getJdbcTemplate().queryForObject("select MISTREAT_NO as MISTREAT_NO from MAS_PERSON where PERSON_ID = '"+req.getPERSON_ID()+"'", String.class);
			
			int value = Integer.parseInt(MISTREAT_NO) + 1;

			StringBuilder sqlBuilder = new StringBuilder()
			.append(" UPDATE MAS_PERSON " +
					" SET MISTREAT_NO = '"+value+"' " +
					" WHERE PERSON_ID = '"+req.getPERSON_ID()+"' ");

			log.info("[SQL] : "+sqlBuilder.toString());
			getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
			
			response.setIsSuccess(Message.TRUE);
			response.setMsg(Message.COMPLETE);
			response.setMISTREAT_NO(value);
		    
		    return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setIsSuccess(Message.FALSE);
			response.setMsg(Message.COMPLETE);
			response.setMISTREAT_NO(null);
		    
		    return response;
		}
		
	}

	@Override
	public LawsuitMistreatNoResponse LawsuitMistreatNoDelete(LawsuitMistreatNoDeleteReq req) {
		
		LawsuitMistreatNoResponse response = new LawsuitMistreatNoResponse();
		
		try {
			
			String MISTREAT_NO = getJdbcTemplate().queryForObject("select MISTREAT_NO as MISTREAT_NO from MAS_PERSON where PERSON_ID = '"+req.getPERSON_ID()+"'", String.class);
			
			int value = Integer.parseInt(MISTREAT_NO) - 1;

			StringBuilder sqlBuilder = new StringBuilder()
			.append(" UPDATE MAS_PERSON " +
					" SET MISTREAT_NO = '"+value+"' " +
					" WHERE PERSON_ID = '"+req.getPERSON_ID()+"' ");

			log.info("[SQL] : "+sqlBuilder.toString());
			getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
			
			response.setIsSuccess(Message.TRUE);
			response.setMsg(Message.COMPLETE);
			response.setMISTREAT_NO(value);
		    
		    return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setIsSuccess(Message.FALSE);
			response.setMsg(Message.COMPLETE);
			response.setMISTREAT_NO(null);
		    
		    return response;
		}
		
	}
}
