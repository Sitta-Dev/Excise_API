package com.xcs.phase2.controller.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidenceout.EvidenceOutDetailDAO;
import com.xcs.phase2.model.evidenceout.EvidenceOutDetail;
import com.xcs.phase2.request.evidenceout.EvidenceOutDetailupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutDetailinsAllResponse;
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
public class EvidenceOutDetailController {
	
	private static final Logger log = LoggerFactory.getLogger(EvidenceOutDetailController.class);

	@Autowired
	private EvidenceOutDetailDAO evidenceOutDetailDAO;
	
	@PostMapping(value = "/EvidenceOutDetailinsAll")
	public ResponseEntity EvidenceOutDetailinsAll(@RequestBody List<EvidenceOutDetail> req) {
		
		log.info("============= Start API EvidenceOutDetailinsAll ================");
		EvidenceOutDetailinsAllResponse res = null;
		try {
			
			res = evidenceOutDetailDAO.EvidenceOutDetailinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API EvidenceOutDetailinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/EvidenceOutDetailupdByCon")
	public ResponseEntity EvidenceOutDetailupdByCon(@RequestBody List<EvidenceOutDetail> req) {
		
		log.info("============= Start API EvidenceOutDetailupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(evidenceOutDetailDAO.EvidenceOutDetailupdByCon(req)){
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
		log.info("============= End API EvidenceOutDetailupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}

	@PostMapping(value = "/EvidenceOutDetailupdDelete")
	public ResponseEntity EvidenceOutDetailupdDelete(@RequestBody EvidenceOutDetailupdDeleteReq req) {
		
		log.info("============= Start API EvidenceOutDetailupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(evidenceOutDetailDAO.EvidenceOutDetailupdDelete(req)){
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
		log.info("============= End API EvidenceOutDetailupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
