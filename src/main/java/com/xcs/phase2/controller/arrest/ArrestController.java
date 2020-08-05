package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestDAO;
import com.xcs.phase2.model.arrest.Arrest;
import com.xcs.phase2.request.arrest.ArrestgetByConReq;
import com.xcs.phase2.request.arrest.ArrestupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.arrest.ArrestinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ArrestController {

	private static final Logger log = LoggerFactory.getLogger(ArrestController.class);

	@Autowired
	private ArrestDAO arrestDAO;
	
	@PostMapping(value = "/ArrestgetByCon")
	public ResponseEntity EvidenceOutgetByCon(@RequestBody ArrestgetByConReq req) {
		

		log.info("============= Start API ArrestgetByCon ================");
		MessageResponse msg = new MessageResponse();
		Arrest res = null;
		Boolean checkType = true;
		try {
			
			res = arrestDAO.ArrestgetByCon(req);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API ArrestgetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestinsAll")
	public ResponseEntity ArrestinsAll(@RequestBody Arrest req) {
		
		log.info("============= Start API EvidenceOutinsAll ================");
		ArrestinsAllResponse res = null;
		try {
			
			res = arrestDAO.ArrestinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API EvidenceOutinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestupdByCon")
	public ResponseEntity ArrestupdByCon(@RequestBody Arrest req) {
		
		log.info("============= Start API ArrestupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestDAO.ArrestupdByCon(req)){
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
		log.info("============= End API ArrestupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestupdDelete")
	public ResponseEntity EvidenceOutupdDelete(@RequestBody ArrestupdDeleteReq req) {
		
		log.info("============= Start API ArrestupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestDAO.ArrestupdDelete(req)){
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
		log.info("============= End API ArrestupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
