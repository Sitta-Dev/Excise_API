package com.xcs.phase2.controller.master;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductUnitMappingDAO;
import com.xcs.phase2.model.master.MasProductUnitMapping;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductUnitMappinginsAllResponse;


@RestController
public class MasProductUnitMappingController {
	private static final Logger log = LoggerFactory.getLogger(MasProductUnitMappingController.class);
	
	@Autowired
    private MasProductUnitMappingDAO masProductUnitMappingDAO;
	
	 @PostMapping(value = "/MasProductUnitMappinginsAll")
	    public ResponseEntity MasProductUnitMappinginsAll(@RequestBody List<MasProductUnitMapping> req) {


	        log.info("============= Start API MasProductUnitMappinginsAll ================");
	        MessageResponse msg = new MessageResponse();
	        MasProductUnitMappinginsAllResponse res = null;
	        Boolean checkType = true;
	        try {

	            res = masProductUnitMappingDAO.MasProductUnitMappinginsAll(req);

	        } catch (Exception e) {
	            checkType = false;
	            msg.setIsSuccess(Message.FALSE);
	            msg.setMsg(e.getMessage());

	        }
	        log.info("============= End API MasProductUnitMappinginsAll =================");
	        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	    }
	 
	 @PostMapping(value = "/MasProductUnitMappingupdAll")
		public ResponseEntity MasProductUnitMappingupdAll(@RequestBody List<MasProductUnitMapping> req) {
			
			log.info("============= Start API MasProductUnitMappingupdAll ================");
			
			MessageResponse msg = new MessageResponse();
			try {
				if(masProductUnitMappingDAO.MasProductUnitMappingupdAll(req)){
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
			log.info("============= End API MasProductUnitMappingupdAll =================");
			return new ResponseEntity(msg, HttpStatus.OK);
		}
	 
	 @PostMapping(value = "/MasProductUnitMappingupdDelete")
		public ResponseEntity MasProductUnitMappingupdDelete(@RequestBody List<MasProductUnitMapping> req) {
			
			log.info("============= Start API MasProductUnitMappingupdDelete ================");
			
			MessageResponse msg = new MessageResponse();
			try {
				if(masProductUnitMappingDAO.MasProductUnitMappingupdDelete(req)){
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
			log.info("============= End API MasProductUnitMappingupdDelete =================");
			return new ResponseEntity(msg, HttpStatus.OK);
		}

}
