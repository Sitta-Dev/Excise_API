package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestListDAO;
import com.xcs.phase2.model.arrest.ArrestLawbreakerPerson;
import com.xcs.phase2.model.arrest.ArrestList;
import com.xcs.phase2.model.arrest.ArrestPerson;
import com.xcs.phase2.request.arrest.ArrestListgetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestListgetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestgetByPersonIdReq;
import com.xcs.phase2.request.arrest.LawbreakerRelationshipgetByPersonIdReq;
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
public class ArrestListController {

	private static final Logger log = LoggerFactory.getLogger(ArrestListController.class);

	@Autowired
	private ArrestListDAO arrestListDAO;
	
	@PostMapping(value = "/ArrestListgetByKeyword")
	public ResponseEntity ArrestListgetByKeyword(@RequestBody ArrestListgetByKeywordReq req) {

		log.info("============= Start API ArrestListgetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestList> res = null;
		Boolean checkType = true;
		try {

			res = arrestListDAO.ArrestListgetByKeyword(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestListgetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestListgetByConAdv")
	public ResponseEntity ArrestListgetByConAdv(@RequestBody ArrestListgetByConAdvReq req) {

		log.info("============= Start API ArrestListgetByConAdv ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestList> res = null;
		Boolean checkType = true;
		try {

			res = arrestListDAO.ArrestListgetByConAdv(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestListgetByConAdv =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}

	@PostMapping(value = "/ArrestgetByPersonId")
	public ResponseEntity ArrestgetByPersonId(@RequestBody ArrestgetByPersonIdReq req) {

		log.info("============= Start API ArrestgetByPersonId ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestPerson> res = null;
		Boolean checkType = true;
		try {

			res = arrestListDAO.ArrestgetByPersonId(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestgetByPersonId =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}

	@PostMapping(value = "/LawbreakerRelationshipgetByPersonId")
	public ResponseEntity LawbreakerRelationshipgetByPersonId(@RequestBody LawbreakerRelationshipgetByPersonIdReq req) {

		log.info("============= Start API LawbreakerRelationshipgetByPersonId ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestLawbreakerPerson> res = null;
		Boolean checkType = true;
		try {

			res = arrestListDAO.LawbreakerRelationshipgetByPersonId(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API LawbreakerRelationshipgetByPersonId =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
