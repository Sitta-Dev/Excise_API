package com.xcs.phase2.controller.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.lawsult.LawsuitListDAO;
import com.xcs.phase2.model.lawsult.LawsuitList;
import com.xcs.phase2.request.lawsult.LawsuiltListgetByConAdvReq;
import com.xcs.phase2.request.lawsult.LawsuiltListgetByKeywordReq;
import com.xcs.phase2.request.lawsult.LawsuiltListgetByLawbreakerReq;
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
public class LawsuitListController {

	private static final Logger log = LoggerFactory.getLogger(LawsuitListController.class);

	@Autowired
	private LawsuitListDAO lawsuitListDAO;
	
	@PostMapping(value = "/LawsuiltListgetByKeyword")
	public ResponseEntity LawsuiltListgetByKeyword(@RequestBody LawsuiltListgetByKeywordReq req) {

		log.info("============= Start API LawsuiltListgetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<LawsuitList> res = null;
		Boolean checkType = true;
		try {

			res = lawsuitListDAO.LawsuiltListgetByKeyword(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API LawsuiltListgetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuiltListgetByConAdv")
	public ResponseEntity LawsuiltListgetByConAdv(@RequestBody LawsuiltListgetByConAdvReq req) {

		log.info("============= Start API LawsuiltListgetByConAdv ================");
		MessageResponse msg = new MessageResponse();
		List<LawsuitList> res = null;
		Boolean checkType = true;
		try {

			res = lawsuitListDAO.LawsuiltListgetByConAdv(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API LawsuiltListgetByConAdv =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuiltListgetByLawbreaker")
	public ResponseEntity LawsuiltListgetByLawbreaker(@RequestBody LawsuiltListgetByLawbreakerReq req) {

		log.info("============= Start API LawsuiltListgetByLawbreaker ================");
		MessageResponse msg = new MessageResponse();
		List<LawsuitList> res = null;
		Boolean checkType = true;
		try {

			//res = lawsuitListDAO.LawsuiltListgetByLawbreaker(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API LawsuiltListgetByLawbreaker =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
