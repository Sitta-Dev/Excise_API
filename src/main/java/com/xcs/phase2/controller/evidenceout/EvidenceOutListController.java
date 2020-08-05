package com.xcs.phase2.controller.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidenceout.EvidenceOutListDAO;
import com.xcs.phase2.model.evidenceout.EvidenceOutList;
import com.xcs.phase2.request.evidenceout.EvidenceOutListgetByConAdvReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutListgetByKeywordReq;
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
public class EvidenceOutListController {

	private static final Logger log = LoggerFactory.getLogger(EvidenceOutListController.class);

	@Autowired
	private EvidenceOutListDAO evidenceOutListDAO;

	@PostMapping(value = "/EvidenceOutListgetByKeyword")
	public ResponseEntity EvidenceOutListgetByKeyword(@RequestBody EvidenceOutListgetByKeywordReq req) {

		log.info("============= Start API EvidenceOutListgetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<EvidenceOutList> res = null;
		Boolean checkType = true;
		try {

			res = evidenceOutListDAO.EvidenceOutListgetByKeyword(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API EvidenceOutListgetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}

	@PostMapping(value = "/EvidenceOutListgetByConAdv")
	public ResponseEntity EvidenceOutListgetByConAdv(@RequestBody EvidenceOutListgetByConAdvReq req) {

		log.info("============= Start API EvidenceOutListgetByConAdv ================");
		MessageResponse msg = new MessageResponse();
		List<EvidenceOutList> res = null;
		Boolean checkType = true;
		try {

			res = evidenceOutListDAO.EvidenceOutListgetByConAdv(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API EvidenceOutListgetByConAdv =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
