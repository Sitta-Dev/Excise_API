package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestProductDAO;
import com.xcs.phase2.model.arrest.ArrestProduct;
import com.xcs.phase2.request.arrest.ArrestProductupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.arrest.ArrestProductinsAllResponse;
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
public class ArrestProductController {

	private static final Logger log = LoggerFactory.getLogger(ArrestProductController.class);

	@Autowired
	private ArrestProductDAO arrestProductDAO;
	
	@PostMapping(value = "/ArrestProductinsAll")
	public ResponseEntity ArrestProductinsAll(@RequestBody List<ArrestProduct> req) {
		
		log.info("============= Start API ArrestProductinsAll ================");
		ArrestProductinsAllResponse res = null;
		try {
			
			res = arrestProductDAO.ArrestProductinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API ArrestProductinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestProductupdByCon")
	public ResponseEntity ArrestProductupdByCon(@RequestBody List<ArrestProduct> req) {
		
		log.info("============= Start API ArrestProductupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestProductDAO.ArrestProductupdByCon(req)){
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
		log.info("============= End API ArrestProductupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestProductupdDelete")
	public ResponseEntity ArrestProductupdDelete(@RequestBody List<ArrestProductupdDeleteReq> req) {
		
		log.info("============= Start API ArrestProductupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestProductDAO.ArrestProductupdDelete(req)){
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
		log.info("============= End API ArrestProductupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
