package com.xcs.phase2.controller.lawsult;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.lawsult.LawsuitMistreatNoDAO;
import com.xcs.phase2.request.lawsult.LawsuitMistreatNoDeleteReq;
import com.xcs.phase2.request.lawsult.LawsuitMistreatNoUpdateReq;
import com.xcs.phase2.response.lawsult.LawsuitMistreatNoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LawsuitMistreatNoController {

	private static final Logger log = LoggerFactory.getLogger(LawsuitMistreatNoController.class);

	@Autowired
	private LawsuitMistreatNoDAO lawsuitMistreatNoDAO;
	
	@PostMapping(value = "/LawsuitMistreatNoupByCon")
	public ResponseEntity LawsuitMistreatNoUpdate(@RequestBody LawsuitMistreatNoUpdateReq req) {
		
		log.info("============= Start API LawsuitMistreatNoUpdate ================");
		LawsuitMistreatNoResponse res = null;
		try {
			
			res = lawsuitMistreatNoDAO.LawsuitMistreatNoUpdate(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API LawsuitMistreatNoUpdate =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "/LawsuitMistreatNoupdDelete")
	public ResponseEntity LawsuitMistreatNoDelete(@RequestBody LawsuitMistreatNoDeleteReq req) {
		
		log.info("============= Start API LawsuitMistreatNoDelete ================");
		LawsuitMistreatNoResponse res = null;
		try {
			
			res = lawsuitMistreatNoDAO.LawsuitMistreatNoDelete(req);
			
		} catch (Exception e) {
			res.setIsSuccess(Message.FALSE);
			res.setMsg(e.getMessage());
		}
		log.info("============= End API LawsuitMistreatNoDelete =================");
		return new ResponseEntity(res, HttpStatus.OK);
	}
}
