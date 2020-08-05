package com.xcs.phase2.controller.arrest;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.arrest.ArrestMasGuiltbaseDAO;
import com.xcs.phase2.model.arrest.NewArrestMasGuiltbase;
import com.xcs.phase2.request.arrest.ArrestMasGuiltbasegetByKeywordReq;
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
public class ArrestMasGuiltbaseController {

	private static final Logger log = LoggerFactory.getLogger(ArrestMasGuiltbaseController.class);

	@Autowired
	private ArrestMasGuiltbaseDAO arrestMasGuiltbaseDAO;
	
	@PostMapping(value = "/ArrestMasGuiltbasegetByKeyword")
	public ResponseEntity ArrestMasGuiltbasegetByKeyword(@RequestBody ArrestMasGuiltbasegetByKeywordReq req) {

		log.info("============= Start API ArrestMasGuiltbasegetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<NewArrestMasGuiltbase> res = null;
		Boolean checkType = true;
		try {

			res = arrestMasGuiltbaseDAO.NewArrestMasGuiltbasegetByKeyword(req);

		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());

		}
		log.info("============= End API ArrestMasGuiltbasegetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
