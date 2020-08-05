package com.xcs.phase2.controller.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidenceout.EvidenceOutDAO;
import com.xcs.phase2.model.evidenceout.EvidenceOut;
import com.xcs.phase2.request.evidenceout.EvidenceOutgetByConReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EvidenceOutController {

	private static final Logger log = LoggerFactory.getLogger(EvidenceOutController.class);

	@Autowired
	private EvidenceOutDAO evidenceOutDAO;
	
	@PostMapping(value = "/EvidenceOutgetByCon")
	public ResponseEntity EvidenceOutgetByCon(@RequestBody EvidenceOutgetByConReq req) {
		

		log.info("============= Start API EvidenceOutgetByCon ================");
		MessageResponse msg = new MessageResponse();
		EvidenceOut res = null;
		Boolean checkType = true;
		try {
			
			res = evidenceOutDAO.EvidenceOutgetByCon(req);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API EvidenceOutgetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/EvidenceOutinsAll")
	public ResponseEntity EvidenceOutinsAll(@RequestBody EvidenceOut req) {
		
		log.info("============= Start API EvidenceOutinsAll ================");
		EvidenceOutinsAllResponse res = null;
		try {
			
			res = evidenceOutDAO.EvidenceOutinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API EvidenceOutinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/EvidenceOutupdByCon")
	public ResponseEntity EvidenceOutupdByCon(@RequestBody EvidenceOut req) {
		
		log.info("============= Start API EvidenceOutupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(evidenceOutDAO.EvidenceOutupdByCon(req)){
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
		log.info("============= End API EvidenceOutupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/EvidenceOutupdDelete")
	public ResponseEntity EvidenceOutupdDelete(@RequestBody EvidenceOutupdDeleteReq req) {
		
		log.info("============= Start API EvidenceOutupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(evidenceOutDAO.EvidenceOutupdDelete(req)){
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
		log.info("============= End API EvidenceOutupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
