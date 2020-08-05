package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestMasPersonDAO;
import com.xcs.phase2.model.arrest.ArrestMasPerson;
import com.xcs.phase2.request.arrest.ArrestMasPersongetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestMasPersongetByKeywordReq;
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
public class ArrestMasPersonController {

	private static final Logger log = LoggerFactory.getLogger(ArrestMasPersonController.class);

	@Autowired
	private ArrestMasPersonDAO arrestMasPersonDAO;
	
	@PostMapping(value = "/ArrestMasPersongetByKeyword")
	public ResponseEntity ArrestMasPersongetByKeyword(@RequestBody ArrestMasPersongetByKeywordReq req) {

		log.info("============= Start API ArrestMasPersongetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestMasPerson> res = null;
		Boolean checkType = true;
		try {

			res = arrestMasPersonDAO.ArrestMasPersongetByKeyword(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestMasPersongetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestMasPersongetByConAdv")
	public ResponseEntity ArrestMasPersongetByConAdv(@RequestBody ArrestMasPersongetByConAdvReq req) {

		log.info("============= Start API ArrestMasPersongetByConAdv ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestMasPerson> res = null;
		Boolean checkType = true;
		try {

			res = arrestMasPersonDAO.ArrestMasPersongetByConAdv(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestMasPersongetByConAdv =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
