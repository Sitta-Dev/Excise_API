package com.xcs.phase2.controller.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidenceout.EvidenceOutItemDAO;
import com.xcs.phase2.model.evidenceout.EvidenceOutItem;
import com.xcs.phase2.model.evidenceout.EvidenceOutItemNew;
import com.xcs.phase2.request.evidenceout.EvidenceOutItemupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutIteminsAllResponse;
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
public class EvidenceOutItemController {
	
	private static final Logger log = LoggerFactory.getLogger(EvidenceOutItemController.class);

	@Autowired
	private EvidenceOutItemDAO evidenceOutItemDAO;
	
	@PostMapping(value = "/EvidenceOutIteminsAll")
	public ResponseEntity EvidenceOutIteminsAll(@RequestBody List<EvidenceOutItemNew> req) {
		
		log.info("============= Start API EvidenceOutIteminsAll ================");
		EvidenceOutIteminsAllResponse res = null;
		try {
			
			res = evidenceOutItemDAO.EvidenceOutIteminsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API EvidenceOutIteminsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/EvidenceOutItemupdByCon")
	public ResponseEntity EvidenceOutItemupdByCon(@RequestBody List<EvidenceOutItem> req) {
		
		log.info("============= Start API EvidenceOutItemupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(evidenceOutItemDAO.EvidenceOutItemupdByCon(req)){
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
		log.info("============= End API EvidenceOutItemupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}

	@PostMapping(value = "/EvidenceOutItemupdDelete")
	public ResponseEntity EvidenceOutItemupdDelete(@RequestBody List<EvidenceOutItemupdDeleteReq> req) {
		
		log.info("============= Start API EvidenceOutItemupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(evidenceOutItemDAO.EvidenceOutItemupdDelete(req)){
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
		log.info("============= End API EvidenceOutItemupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
