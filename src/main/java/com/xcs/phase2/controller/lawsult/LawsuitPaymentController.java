package com.xcs.phase2.controller.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.lawsult.LawsuitPaymentDAO;
import com.xcs.phase2.model.lawsult.LawsuitPayment;
import com.xcs.phase2.request.lawsult.LawsuitPaymentgetByConReq;
import com.xcs.phase2.request.lawsult.LawsuitPaymentupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.lawsult.LawsuitPaymentinsAllResposne;
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
public class LawsuitPaymentController {

	private static final Logger log = LoggerFactory.getLogger(LawsuitPaymentController.class);

	@Autowired
	private LawsuitPaymentDAO lawsuitPaymentDAO;
	
	@PostMapping(value = "/LawsuitPaymentinsAll")
	public ResponseEntity LawsuitPaymentinsAll(@RequestBody List<LawsuitPayment> req) {
		
		log.info("============= Start API LawsuitPaymentinsAll ================");
		LawsuitPaymentinsAllResposne res = null;
		try {
			
			res = lawsuitPaymentDAO.LawsuitPaymentinsAll(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API LawsuitPaymentinsAll =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuitPaymentupdDelete")
	public ResponseEntity LawsuitPaymentupdDelete(@RequestBody List<LawsuitPaymentupdDeleteReq> req) {
		
		log.info("============= Start API LawsuitPaymentupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(lawsuitPaymentDAO.LawsuitPaymentupdDelete(req)){
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
		log.info("============= End API LawsuitPaymentupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}

	@PostMapping(value = "/LawsuitPaymentgetByCon")
	public ResponseEntity LawsuitPaymentgetByCon(@RequestBody LawsuitPaymentgetByConReq req) {

		log.info("============= Start API LawsuitPaymentgetByCon ================");
		MessageResponse msg = new MessageResponse();
		List<LawsuitPayment> res = null;
		Boolean checkType = true;
		try {

			res = lawsuitPaymentDAO.LawsuitPaymentgetByCon(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API LawsuitPaymentgetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
