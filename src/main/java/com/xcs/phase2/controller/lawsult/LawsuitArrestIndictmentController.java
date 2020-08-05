package com.xcs.phase2.controller.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.lawsult.LawsuitArrestIndictmentDAO;
import com.xcs.phase2.model.lawsult.LawsuitArrestIndictment;
import com.xcs.phase2.request.lawsult.*;
import com.xcs.phase2.response.MessageResponse;
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
public class LawsuitArrestIndictmentController {

	private static final Logger log = LoggerFactory.getLogger(LawsuitArrestIndictmentController.class);

	@Autowired
	private LawsuitArrestIndictmentDAO lawsuitArrestIndictmentDAO;
	
	@PostMapping(value = "/LawsuiltArrestIndictmentgetByCon")
	public ResponseEntity LawsuiltArrestIndictmentgetByCon(@RequestBody LawsuiltArrestIndictmentgetByConReq req) {
		

		log.info("============= Start API LawsuiltArrestIndictmentgetByCon ================");
		MessageResponse msg = new MessageResponse();
		LawsuitArrestIndictment res = null;
		Boolean checkType = true;
		try {
			
			res = lawsuitArrestIndictmentDAO.LawsuiltArrestIndictmentgetByCon(req);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API LawsuiltArrestIndictmentgetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuiltArrestIndictmentupdIndictmentComplete")
	public ResponseEntity LawsuiltArrestIndictmentupdIndictmentComplete(@RequestBody LawsuiltArrestIndictmentupdIndictmentCompleteReq req) {
		
		log.info("============= Start API LawsuiltArrestIndictmentupdIndictmentComplete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(lawsuitArrestIndictmentDAO.LawsuiltArrestIndictmentupdIndictmentComplete(req)){
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
		log.info("============= End API LawsuiltArrestIndictmentupdIndictmentComplete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuiltArrestIndictmentupdDeleteIndictmentComplete")
	public ResponseEntity LawsuiltArrestIndictmentupdDeleteIndictmentComplete(@RequestBody LawsuiltArrestIndictmentupdDeleteIndictmentCompleteReq req) {
		
		log.info("============= Start API LawsuiltArrestIndictmentupdDeleteIndictmentComplete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(lawsuitArrestIndictmentDAO.LawsuiltArrestIndictmentupdDeleteIndictmentComplete(req)){
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
		log.info("============= End API LawsuiltArrestIndictmentupdDeleteIndictmentComplete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuiltArrestIndictmentupdArrestComplete")
	public ResponseEntity LawsuiltArrestIndictmentupdArrestComplete(@RequestBody LawsuiltArrestIndictmentupdArrestCompleteReq req) {
		
		log.info("============= Start API LawsuiltArrestIndictmentupdArrestComplete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(lawsuitArrestIndictmentDAO.LawsuiltArrestIndictmentupdArrestComplete(req)){
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
		log.info("============= End API LawsuiltArrestIndictmentupdArrestComplete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuiltArrestIndictmentupdDeleteArrestComplete")
	public ResponseEntity LawsuiltArrestIndictmentupdDeleteArrestComplete(@RequestBody LawsuiltArrestIndictmentupdDeleteArrestCompleteReq req) {
		
		log.info("============= Start API LawsuiltArrestIndictmentupdDeleteArrestComplete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(lawsuitArrestIndictmentDAO.LawsuiltArrestIndictmentupdDeleteArrestComplete(req)){
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
		log.info("============= End API LawsuiltArrestIndictmentupdDeleteArrestComplete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuiltArrestIndictmentCheckComplete")
	public ResponseEntity LawsuiltArrestIndictmentCheckComplete(@RequestBody LawsuiltArrestIndictmentCheckNotCompleteReq req) {
		

		log.info("============= Start API LawsuiltArrestIndictmentCheckComplete ================");
		MessageResponse msg = new MessageResponse();
		List<LawsuitArrestIndictment> res = null;
		Boolean checkType = true;
		try {
			
			res = lawsuitArrestIndictmentDAO.LawsuiltArrestIndictmentCheckComplete(req);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API LawsuiltArrestIndictmentCheckComplete =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
