package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestSearchWarrantDAO;
import com.xcs.phase2.model.arrest.ArrestSearchWarrant;
import com.xcs.phase2.request.arrest.ArrestSearchWarrantgetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestSearchWarrantgetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestSearchWarrantupdByConReq;
import com.xcs.phase2.request.arrest.ArrestSearchWarrantupdDeleteReq;
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
public class ArrestSearchWarrantController {

	private static final Logger log = LoggerFactory.getLogger(ArrestSearchWarrantController.class);

	@Autowired
	private ArrestSearchWarrantDAO arrestSearchWarrantDAO;
	
	@PostMapping(value = "/ArrestSearchWarrantgetByKeyword")
	public ResponseEntity ArrestSearchWarrantgetByKeyword(@RequestBody ArrestSearchWarrantgetByKeywordReq req) {

		log.info("============= Start API ArrestSearchWarrantgetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestSearchWarrant> res = null;
		Boolean checkType = true;
		try {

			res = arrestSearchWarrantDAO.ArrestSearchWarrantgetByKeyword(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestSearchWarrantgetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestSearchWarrantgetByConAdv")
	public ResponseEntity ArrestSearchWarrantgetByConAdv(@RequestBody ArrestSearchWarrantgetByConAdvReq req) {

		log.info("============= Start API ArrestSearchWarrantgetByConAdv ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestSearchWarrant> res = null;
		Boolean checkType = true;
		try {

			res = arrestSearchWarrantDAO.ArrestSearchWarrantgetByConAdv(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestSearchWarrantgetByConAdv =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestSearchWarrantupdByCon")
	public ResponseEntity ArrestSearchWarrantupdByCon(@RequestBody List<ArrestSearchWarrantupdByConReq> req) {
		
		log.info("============= Start API ArrestSearchWarrantupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestSearchWarrantDAO.ArrestSearchWarrantupdByCon(req)){
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
		log.info("============= End API ArrestSearchWarrantupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestSearchWarrantupdDelete")
	public ResponseEntity ArrestSearchWarrantupdDelete(@RequestBody List<ArrestSearchWarrantupdDeleteReq> req) {
		
		log.info("============= Start API ArrestSearchWarrantupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestSearchWarrantDAO.ArrestSearchWarrantupdDelete(req)){
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
		log.info("============= End API ArrestSearchWarrantupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
