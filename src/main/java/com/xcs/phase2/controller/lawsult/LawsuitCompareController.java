package com.xcs.phase2.controller.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.lawsult.LawsuitCompareDAO;
import com.xcs.phase2.model.lawsult.LawsuitCompare;
import com.xcs.phase2.request.lawsult.LawsuiltComparegetByLawsuitIDReq;
import com.xcs.phase2.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LawsuitCompareController {

	private static final Logger log = LoggerFactory.getLogger(LawsuitCompareController.class);

	@Autowired
	private LawsuitCompareDAO lawsuitCompareDAO;
	
	
	@PostMapping(value = "/LawsuiltComparegetByLawsuitID")
	public ResponseEntity LawsuiltComparegetByLawsuitID(@RequestBody LawsuiltComparegetByLawsuitIDReq req) {

		log.info("============= Start API LawsuiltComparegetByLawsuitID ================");
		MessageResponse msg = new MessageResponse();
		LawsuitCompare res = null;
		Boolean checkType = true;
		try {

			res = lawsuitCompareDAO.LawsuiltComparegetByLawsuitID(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API LawsuiltComparegetByLawsuitID =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
