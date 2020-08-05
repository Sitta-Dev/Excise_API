package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestIndictmentDAO;
import com.xcs.phase2.model.arrest.ArrestIndictment;
import com.xcs.phase2.request.arrest.ArrestIndictmentupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.arrest.ArrestIndictmentinsAllResponse;
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
public class ArrestIndictmentController {

	private static final Logger log = LoggerFactory.getLogger(ArrestIndictmentController.class);

	@Autowired
	private ArrestIndictmentDAO arrestIndictmentDAO;
	
	@PostMapping(value = "/ArrestIndictmentinsAll")
	public ResponseEntity ArrestIndictmentinsAll(@RequestBody List<ArrestIndictment> req) {
		
		log.info("============= Start API ArrestIndictmentinsAll ================");
		ArrestIndictmentinsAllResponse res = null;
		try {
			
			res = arrestIndictmentDAO.ArrestIndictmentinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API ArrestIndictmentinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestIndictmentupdByCon")
	public ResponseEntity ArrestIndictmentupdByCon(@RequestBody List<ArrestIndictment> req) {
		
		log.info("============= Start API ArrestIndictmentupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestIndictmentDAO.ArrestIndictmentupdByCon(req)){
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
		log.info("============= End API ArrestIndictmentupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestIndictmentupdDelete")
	public ResponseEntity ArrestIndictmentupdDelete(@RequestBody List<ArrestIndictmentupdDeleteReq> req) {
		
		log.info("============= Start API ArrestIndictmentupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestIndictmentDAO.ArrestIndictmentupdDelete(req)){
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
		log.info("============= End API ArrestIndictmentupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
