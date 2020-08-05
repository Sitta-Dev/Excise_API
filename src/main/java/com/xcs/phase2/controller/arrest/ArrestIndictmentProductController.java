package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestIndictmentProductDAO;
import com.xcs.phase2.model.arrest.ArrestIndictmentProduct;
import com.xcs.phase2.request.arrest.ArrestIndictmentProductupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.arrest.ArrestIndictmentProductinsAllResponse;
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
public class ArrestIndictmentProductController {

	private static final Logger log = LoggerFactory.getLogger(ArrestIndictmentProductController.class);

	@Autowired
	private ArrestIndictmentProductDAO arrestIndictmentProductDAO;
	
	@PostMapping(value = "/ArrestIndictmentProductinsAll")
	public ResponseEntity ArrestIndictmentProductinsAll(@RequestBody List<ArrestIndictmentProduct> req) {
		
		log.info("============= Start API ArrestIndictmentProductinsAll ================");
		ArrestIndictmentProductinsAllResponse res = null;
		try {
			
			res = arrestIndictmentProductDAO.ArrestIndictmentProductinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API ArrestIndictmentProductinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestIndictmentProductupdByCon")
	public ResponseEntity ArrestIndictmentProductupdByCon(@RequestBody List<ArrestIndictmentProduct> req) {
		
		log.info("============= Start API ArrestIndictmentProductupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestIndictmentProductDAO.ArrestIndictmentProductupdByCon(req)){
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
		log.info("============= End API ArrestIndictmentProductupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestIndictmentProductupdDelete")
	public ResponseEntity ArrestIndictmentProductupdDelete(@RequestBody List<ArrestIndictmentProductupdDeleteReq> req) {
		
		log.info("============= Start API ArrestIndictmentProductupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestIndictmentProductDAO.ArrestIndictmentProductupdDelete(req)){
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
		log.info("============= End API ArrestIndictmentProductupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
