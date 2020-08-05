package com.xcs.phase2.controller.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.lawsult.LawsuitDAO;
import com.xcs.phase2.model.lawsult.Lawsuit;
import com.xcs.phase2.request.lawsult.LawsuitVerifyLawsuitNoReq;
import com.xcs.phase2.request.lawsult.LawsuitgetByConReq;
import com.xcs.phase2.request.lawsult.LawsuitupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.lawsult.LawsuitinsAllResponse;
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
public class LawsuitController {

	private static final Logger log = LoggerFactory.getLogger(LawsuitController.class);

	@Autowired
	private LawsuitDAO lawsuitDAO;
	
	@PostMapping(value = "/LawsuitinsAll")
	public ResponseEntity LawsuitinsAll(@RequestBody Lawsuit req) {
		
		log.info("============= Start API LawsuitinsAll ================");
		LawsuitinsAllResponse res = null;
		try {
			
			res = lawsuitDAO.LawsuitinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API LawsuitinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuitupdAll")
	public ResponseEntity LawsuitupdAll(@RequestBody Lawsuit req) {
		
		log.info("============= Start API LawsuitupdAll ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(lawsuitDAO.LawsuitupdAll(req)){
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
		log.info("============= End API LawsuitupdAll =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuitupdDelete")
	public ResponseEntity LawsuitupdDelete(@RequestBody LawsuitupdDeleteReq req) {
		
		log.info("============= Start API LawsuitupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(lawsuitDAO.LawsuitupdDelete(req)){
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
		log.info("============= End API LawsuitupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuitgetByCon")
	public ResponseEntity LawsuitgetByCon(@RequestBody LawsuitgetByConReq req) {
		

		log.info("============= Start API LawsuitgetByCon ================");
		MessageResponse msg = new MessageResponse();
		Lawsuit res = null;
		Boolean checkType = true;
		try {
			
			res = lawsuitDAO.LawsuitgetByCon(req);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API LawsuitgetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}

	@PostMapping(value = "/LawsuitVerifyLawsuitNo")
	public ResponseEntity LawsuitVerifyLawsuitNo(@RequestBody LawsuitVerifyLawsuitNoReq req) {

		log.info("============= Start API LawsuitVerifyLawsuitNo ================");
		MessageResponse msg = new MessageResponse();
		List<Lawsuit> res = null;
		Boolean checkType = true;
		try {

			res = lawsuitDAO.LawsuitVerifyLawsuitNo(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API LawsuitVerifyLawsuitNo =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
