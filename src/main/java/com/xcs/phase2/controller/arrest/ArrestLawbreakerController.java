package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestLawbreakerDAO;
import com.xcs.phase2.model.arrest.ArrestLawbreaker;
import com.xcs.phase2.request.arrest.ArrestLawbreakerupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.arrest.ArrestLawbreakerinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ArrestLawbreakerController {

	private static final Logger log = LoggerFactory.getLogger(ArrestLawbreakerController.class);

	@Autowired
	private ArrestLawbreakerDAO arrestLawbreakerDAO;
	
	@PostMapping(value = "/ArrestLawbreakerinsAll")
	public ResponseEntity ArrestLawbreakerinsAll(@RequestBody List<ArrestLawbreaker> req) {
		
		log.info("============= Start API ArrestLawbreakerinsAll ================");
		ArrestLawbreakerinsAllResponse res = null;
		try {
			
			res = arrestLawbreakerDAO.ArrestLawbreakerinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API ArrestLawbreakerinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestLawbreakerupdDelete")
	public ResponseEntity ArrestLawbreakerupdDelete(@RequestBody List<ArrestLawbreakerupdDeleteReq> req) {
		
		log.info("============= Start API ArrestLawbreakerupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestLawbreakerDAO.ArrestLawbreakerupdDelete(req)){
				msg.setIsSuccess(Message.TRUE);
				msg.setMsg(Message.COMPLETE);
			}else{
				msg.setIsSuccess(Message.FALSE);
				msg.setMsg(Message.NOT_COMPLETE);
			}
			
		} catch (Exception e) {
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
		}
		log.info("============= End API ArrestLawbreakerupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
