package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestStaffDAO;
import com.xcs.phase2.model.arrest.ArrestStaff;
import com.xcs.phase2.request.arrest.ArrestStaffupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.arrest.ArrestStaffinsAllResponse;
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
public class ArrestStaffController {

	private static final Logger log = LoggerFactory.getLogger(ArrestStaffController.class);

	@Autowired
	private ArrestStaffDAO arrestStaffDAO;
	
	@PostMapping(value = "/ArrestStaffinsAll")
	public ResponseEntity ArrestStaffinsAll(@RequestBody List<ArrestStaff> req) {
		
		log.info("============= Start API ArrestStaffinsAll ================");
		ArrestStaffinsAllResponse res = null;
		try {
			
			res = arrestStaffDAO.ArrestStaffinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API ArrestStaffinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestStaffupdByCon")
	public ResponseEntity ArrestStaffupdByCon(@RequestBody List<ArrestStaff> req) {
		
		log.info("============= Start API ArrestStaffupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestStaffDAO.ArrestStaffupdByCon(req)){
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
		log.info("============= End API ArrestStaffupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestStaffupdDelete")
	public ResponseEntity ArrestStaffupdDelete(@RequestBody List<ArrestStaffupdDeleteReq> req) {
		
		log.info("============= Start API ArrestStaffupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestStaffDAO.ArrestStaffupdDelete(req)){
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
		log.info("============= End API ArrestStaffupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
