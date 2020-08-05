package com.xcs.phase2.controller.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidenceout.EvidenceOutInTypeDAO;
import com.xcs.phase2.model.evidenceout.EvidenceOutInType;
import com.xcs.phase2.request.evidenceout.EvidenceOutInTypegetByConReq;
import com.xcs.phase2.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EvidenceOutInTypeController {

	private static final Logger log = LoggerFactory.getLogger(EvidenceOutInTypeController.class);

	@Autowired
	private EvidenceOutInTypeDAO evidenceOutInTypeDAO;
	
	@PostMapping(value = "/EvidenceOutInTypegetByCon")
	public ResponseEntity EvidenceOutInTypegetByCon(@RequestBody EvidenceOutInTypegetByConReq req) {
		

		log.info("============= Start API EvidenceOutInTypegetByCon ================");
		MessageResponse msg = new MessageResponse();
		EvidenceOutInType res = null;
		Boolean checkType = true;
		try {
			
			res = evidenceOutInTypeDAO.EvidenceOutInTypegetByCon(req);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API EvidenceOutInTypegetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
