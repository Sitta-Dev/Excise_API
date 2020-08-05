package com.xcs.phase2.controller.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.lawsult.LawsuitStaffDAO;
import com.xcs.phase2.model.lawsult.LawsuitStaff;
import com.xcs.phase2.request.lawsult.LawsuitStaffupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.lawsult.LawsuitStaffinsAllResponse;
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
public class LawsuitStaffController {

	private static final Logger log = LoggerFactory.getLogger(LawsuitStaffController.class);

	@Autowired
	private LawsuitStaffDAO lawsuitStaffDAO;
	
	@PostMapping(value = "/LawsuitStaffinsAll")
	public ResponseEntity LawsuitStaffinsAll(@RequestBody List<LawsuitStaff> req) {
		
		log.info("============= Start API LawsuitStaffinsAll ================");
		LawsuitStaffinsAllResponse res = null;
		try {
			
			res = lawsuitStaffDAO.LawsuitStaffinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API LawsuitStaffinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuitStaffupdAll")
	public ResponseEntity LawsuitStaffUpdate(@RequestBody List<LawsuitStaff> req) {
		
		log.info("============= Start API LawsuitStaffUpdate ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(lawsuitStaffDAO.LawsuitStaffUpdate(req)){
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
		log.info("============= End API LawsuitStaffUpdate =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuitStaffupdDelete")
	public ResponseEntity LawsuitStaffupdDelete(@RequestBody List<LawsuitStaffupdDeleteReq> req) {
		
		log.info("============= Start API LawsuitStaffupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(lawsuitStaffDAO.LawsuitStaffupdDelete(req)){
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
		log.info("============= End API LawsuitStaffupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
