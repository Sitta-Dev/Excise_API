package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestIndictmentDetailDAO;
import com.xcs.phase2.model.arrest.ArrestIndictmentDetail;
import com.xcs.phase2.request.arrest.ArrestIndictmentDetailupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.arrest.ArrestIndictmentDetailinsAllResponse;
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
public class ArrestIndictmentDetailController {

	private static final Logger log = LoggerFactory.getLogger(ArrestIndictmentDetailController.class);

	@Autowired
	private ArrestIndictmentDetailDAO arrestIndictmentDetailDAO;
	
	@PostMapping(value = "/ArrestIndictmentDetailinsAll")
	public ResponseEntity ArrestIndictmentDetailinsAll(@RequestBody List<ArrestIndictmentDetail> req) {
		
		log.info("============= Start API ArrestIndictmentDetailinsAll ================");
		ArrestIndictmentDetailinsAllResponse res = null;
		try {
			
			res = arrestIndictmentDetailDAO.ArrestIndictmentDetailinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API ArrestIndictmentDetailinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	

	
	@PostMapping(value = "/ArrestIndictmentDetailupdDelete")
	public ResponseEntity ArrestIndictmentDetailupdDelete(@RequestBody List<ArrestIndictmentDetailupdDeleteReq> req) {
		
		log.info("============= Start API ArrestIndictmentDetailupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestIndictmentDetailDAO.ArrestIndictmentDetailupdDelete(req)){
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
		log.info("============= End API ArrestIndictmentDetailupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
