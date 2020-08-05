package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestPurityCertDAO;
import com.xcs.phase2.model.arrest.ArrestPurityCert;
import com.xcs.phase2.request.arrest.ArrestPurityCertgetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestPurityCertgetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestPurityCertupdByConReq;
import com.xcs.phase2.request.arrest.ArrestPurityCertupdDeleteReq;
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
public class ArrestPurityCertController {

	private static final Logger log = LoggerFactory.getLogger(ArrestPurityCertController.class);

	@Autowired
	private ArrestPurityCertDAO arrestPurityCertDAO;
	
	@PostMapping(value = "/ArrestPurityCertgetByKeyword")
	public ResponseEntity ArrestPurityCertgetByKeyword(@RequestBody ArrestPurityCertgetByKeywordReq req) {

		log.info("============= Start API ArrestPurityCertgetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestPurityCert> res = null;
		Boolean checkType = true;
		try {

			res = arrestPurityCertDAO.ArrestPurityCertgetByKeyword(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestPurityCertgetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestPurityCertgetByConAdv")
	public ResponseEntity ArrestPurityCertgetByConAdv(@RequestBody ArrestPurityCertgetByConAdvReq req) {

		log.info("============= Start API ArrestPurityCertgetByConAdv ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestPurityCert> res = null;
		Boolean checkType = true;
		try {

			res = arrestPurityCertDAO.ArrestPurityCertgetByConAdv(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestPurityCertgetByConAdv =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestPurityCertupdByCon")
	public ResponseEntity ArrestPurityCertupdByCon(@RequestBody List<ArrestPurityCertupdByConReq> req) {
		
		log.info("============= Start API ArrestPurityCertupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestPurityCertDAO.ArrestPurityCertupdByCon(req)){
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
		log.info("============= End API ArrestPurityCertupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestPurityCertupdDelete")
	public ResponseEntity ArrestPurityCertupdDelete(@RequestBody List<ArrestPurityCertupdDeleteReq> req) {
		
		log.info("============= Start API ArrestPurityCertupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestPurityCertDAO.ArrestPurityCertupdDelete(req)){
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
		log.info("============= End API ArrestPurityCertupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
