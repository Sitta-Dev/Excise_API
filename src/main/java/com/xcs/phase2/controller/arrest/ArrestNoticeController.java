package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestNoticeDAO;
import com.xcs.phase2.model.arrest.ArrestNotice;
import com.xcs.phase2.request.arrest.ArrestNoticegetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestNoticegetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestNoticeupdByConReq;
import com.xcs.phase2.request.arrest.ArrestNoticeupdDeleteReq;
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
public class ArrestNoticeController {

	private static final Logger log = LoggerFactory.getLogger(ArrestNoticeController.class);

	@Autowired
	private ArrestNoticeDAO arrestNoticeDAO;
	
	@PostMapping(value = "/ArrestNoticegetByKeyword")
	public ResponseEntity ArrestNoticegetByKeyword(@RequestBody ArrestNoticegetByKeywordReq req) {

		log.info("============= Start API ArrestNoticegetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestNotice> res = null;
		Boolean checkType = true;
		try {

			res = arrestNoticeDAO.ArrestNoticegetByKeyword(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestNoticegetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestNoticegetByConAdv")
	public ResponseEntity ArrestNoticegetByConAdv(@RequestBody ArrestNoticegetByConAdvReq req) {

		log.info("============= Start API ArrestNoticegetByConAdv ================");
		MessageResponse msg = new MessageResponse();
		List<ArrestNotice> res = null;
		Boolean checkType = true;
		try {

			res = arrestNoticeDAO.ArrestNoticegetByConAdv(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestNoticegetByConAdv =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestNoticeupdByCon")
	public ResponseEntity ArrestNoticeupdByCon(@RequestBody List<ArrestNoticeupdByConReq> req) {
		
		log.info("============= Start API ArrestNoticeupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestNoticeDAO.ArrestNoticeupdByCon(req)){
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
		log.info("============= End API ArrestNoticeupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ArrestNoticeupdDelete")
	public ResponseEntity ArrestNoticeupdDelete(@RequestBody List<ArrestNoticeupdDeleteReq> req) {
		
		log.info("============= Start API ArrestNoticeupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(arrestNoticeDAO.ArrestNoticeupdDelete(req)){
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
		log.info("============= End API ArrestNoticeupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
