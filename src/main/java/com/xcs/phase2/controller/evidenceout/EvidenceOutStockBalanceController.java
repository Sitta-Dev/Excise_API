package com.xcs.phase2.controller.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidenceout.EvidenceOutStockBalanceDAO;
import com.xcs.phase2.request.evidenceout.EvidenceOutStockBalanceupdByConReq;
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
public class EvidenceOutStockBalanceController {

	private static final Logger log = LoggerFactory.getLogger(EvidenceOutStockBalanceController.class);
	
	@Autowired
	private EvidenceOutStockBalanceDAO evidenceOutStockBalanceDAO;
	
	@PostMapping(value = "/EvidenceOutStockBalanceupdByCon")
	public ResponseEntity EvidenceOutStockBalanceupdByCon(@RequestBody EvidenceOutStockBalanceupdByConReq req) {
		
		log.info("============= Start API EvidenceOutStockBalanceupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(evidenceOutStockBalanceDAO.EvidenceOutStockBalanceupdByCon(req)){
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
		log.info("============= End API EvidenceOutStockBalanceupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
}
