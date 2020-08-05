package com.xcs.phase2.controller.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidenceout.EvidenceOutInDAO;
import com.xcs.phase2.model.evidenceout.EvidenceOutIn;
import com.xcs.phase2.request.evidenceout.EvidenceOutIngetByKeywordReq;
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
public class EvidenceOutInController {

	private static final Logger log = LoggerFactory.getLogger(EvidenceOutInController.class);

	@Autowired
	private EvidenceOutInDAO evidenceOutInDAO;

	@PostMapping(value = "/EvidenceOutIngetByKeyword")
	public ResponseEntity EvidenceOutIngetByKeyword(@RequestBody EvidenceOutIngetByKeywordReq req) {

		log.info("============= Start API EvidenceOutIngetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<EvidenceOutIn> res = null;
		Boolean checkType = true;
		try {

			res = evidenceOutInDAO.EvidenceOutIngetByKeyword(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API EvidenceOutIngetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
