package com.xcs.phase2.controller.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.lawsult.LawsuitProveDAO;
import com.xcs.phase2.model.lawsult.LawsuitProve;
import com.xcs.phase2.request.lawsult.LawsuiltProvegetByLawsuitIDReq;
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
public class LawsuitProveController {

	private static final Logger log = LoggerFactory.getLogger(LawsuitProveController.class);

	@Autowired
	private LawsuitProveDAO lawsuitProveDAO;
	
	
	@PostMapping(value = "/LawsuiltProvegetByLawsuitID")
	public ResponseEntity LawsuiltProvegetByLawsuitID(@RequestBody LawsuiltProvegetByLawsuitIDReq req) {

		log.info("============= Start API LawsuiltProvegetByLawsuitID ================");
		MessageResponse msg = new MessageResponse();
		LawsuitProve res = null;
		Boolean checkType = true;
		try {

			res = lawsuitProveDAO.LawsuiltProvegetByLawsuitID(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API LawsuiltProvegetByLawsuitID =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
