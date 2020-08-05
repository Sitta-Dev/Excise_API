package com.xcs.phase2.controller.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.EvidenceInDAO;
import com.xcs.phase2.model.evidencein.EvidenceIn;
import com.xcs.phase2.request.evidencein.EvidenceIngetByConReq;
import com.xcs.phase2.request.evidencein.EvidenceInupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.evidencein.EvidenceIninsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EvidenceInController {

	private static final Logger log = LoggerFactory.getLogger(EvidenceInController.class);

	@Autowired
	private EvidenceInDAO evidenceInDAO;
	
	@PostMapping(value = "/EvidenceIngetByCon")
	public ResponseEntity EvidenceIngetByCon(@RequestBody EvidenceIngetByConReq req) {
		

		log.info("============= Start API EvidenceIngetByCon ================");
		MessageResponse msg = new MessageResponse();
		EvidenceIn res = null;
		Boolean checkType = true;
		try {
			
			res = evidenceInDAO.EvidenceIngetByCon(req);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API EvidenceIngetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/EvidenceIninsAll")
	public ResponseEntity EvidenceIninsAll(@RequestBody EvidenceIn req) {
		
		log.info("============= Start API EvidenceIninsAll ================");
		EvidenceIninsAllResponse res = null;
		try {
			
			res = evidenceInDAO.EvidenceIninsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API EvidenceIninsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/EvidenceInupdByCon")
	public ResponseEntity EvidenceInupdByCon(@RequestBody EvidenceIn req) {
		
		log.info("============= Start API EvidenceInupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(evidenceInDAO.EvidenceInupdByCon(req)){
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
		log.info("============= End API EvidenceInupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/EvidenceInupdDelete")
	public ResponseEntity EvidenceInupdDelete(@RequestBody EvidenceInupdDeleteReq req) {
		
		log.info("============= Start API EvidenceInupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(evidenceInDAO.EvidenceInupdDelete(req)){
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
		log.info("============= End API EvidenceInupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
