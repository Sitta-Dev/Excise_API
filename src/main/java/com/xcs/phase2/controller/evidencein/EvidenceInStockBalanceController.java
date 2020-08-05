package com.xcs.phase2.controller.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.EvidenceInStockBalanceDAO;
import com.xcs.phase2.model.evidencein.EvidenceInStockBalance;
import com.xcs.phase2.model.evidencein.EvidenceInventoryList;
import com.xcs.phase2.model.evidenceout.EvidenceOutStockBalanceByLawsuitNo;
import com.xcs.phase2.request.evidencein.EvidenceInventoryListgetByLawsuitNoReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutStockBalancegetByEvidenceOutIdReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutStockBalancegetByLawsuitNoReq;
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
public class EvidenceInStockBalanceController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInStockBalanceController.class);

    @Autowired
    private EvidenceInStockBalanceDAO evidenceInStockBalanceDAO;

    @PostMapping(value = "/EvidenceInStockBalanceupdByCon")
    public ResponseEntity EvidenceInStockBalanceupdByCon(@RequestBody List<EvidenceInStockBalance> req) {

        log.info("============= Start API EvidenceInStockBalanceupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (evidenceInStockBalanceDAO.EvidenceInStockBalanceupdByCon(req)) {
                msg.setIsSuccess(Message.TRUE);
                msg.setMsg(Message.COMPLETE);
            } else {
                msg.setIsSuccess(Message.FALSE);
                msg.setMsg(Message.NOT_COMPLETE);
            }

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API EvidenceInStockBalanceupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInventoryListgetByLawsuitNo")
    public ResponseEntity EvidenceInventoryListgetByLawsuitNo(@RequestBody EvidenceInventoryListgetByLawsuitNoReq req) {

        log.info("============= Start API EvidenceInventoryListgetByLawsuitNo ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInventoryList> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInStockBalanceDAO.EvidenceInventoryListgetByLawsuitNo(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInventoryListgetByLawsuitNo =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceOutStockBalanceByLawsuitNo")
    public ResponseEntity EvidenceOutStockBalanceByLawsuitNo(@RequestBody EvidenceOutStockBalancegetByLawsuitNoReq req) {

        log.info("============= Start API EvidenceOutStockBalanceByLawsuitNo ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceOutStockBalanceByLawsuitNo> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInStockBalanceDAO.EvidenceOutStockBalanceByLawsuitNo(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceOutStockBalanceByLawsuitNo =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceOutStockBalanceByEvidenceOutId")
    public ResponseEntity EvidenceOutStockBalanceByEvidenceOutId(@RequestBody EvidenceOutStockBalancegetByEvidenceOutIdReq req) {

        log.info("============= Start API EvidenceOutStockBalanceByEvidenceOutId ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceOutStockBalanceByLawsuitNo> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInStockBalanceDAO.EvidenceOutStockBalanceByEvidenceOutId(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceOutStockBalanceByEvidenceOutId =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
