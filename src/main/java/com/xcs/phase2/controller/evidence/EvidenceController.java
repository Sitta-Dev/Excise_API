package com.xcs.phase2.controller.evidence;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.controller.evidencein.EvidenceInController;
import com.xcs.phase2.dao.evidence.EvidenceDAO;
import com.xcs.phase2.model.evidence.*;
import com.xcs.phase2.request.evidence.*;
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
public class EvidenceController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInController.class);

    @Autowired
    private EvidenceDAO evidenceDAO;

    @PostMapping(value = "/EvidenceAccountgetByCon")
    public ResponseEntity EvidenceAccountgetByCon(@RequestBody EvidenceAccountgetByConReq req) {

        log.info("============= Start API EvidenceAccountgetByCon ================");
        MessageResponse msg = new MessageResponse();
        EvidenceAccount res = null;
        Boolean checkType = true;
        try {

            res = evidenceDAO.EvidenceAccountgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceAccountgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceAccountWarehoustgetByCon")
    public ResponseEntity EvidenceAccountWarehoustgetByCon(@RequestBody EvidenceAccountWarehoustgetByConReq req) {

        log.info("============= Start API EvidenceAccountWarehoustgetByCon ================");
        MessageResponse msg = new MessageResponse();
        EvidenceAccountWarehouse res = null;
        Boolean checkType = true;
        try {

            res = evidenceDAO.EvidenceAccountWarehoustgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceAccountWarehoustgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceAccountProductgetByCon")
    public ResponseEntity EvidenceAccountProductgetByCon(@RequestBody EvidenceAccountProductgetByConReq req) {

        log.info("============= Start API EvidenceAccountProductgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceAccountProduct> res = null;
        Boolean checkType = true;
        try {

            res = evidenceDAO.EvidenceAccountProductgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceAccountProductgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceAccountProductDetailgetByCon")
    public ResponseEntity EvidenceAccountProductDetailgetByCon(@RequestBody EvidenceAccountProductDetailgetByConReq req) {

        log.info("============= Start API EvidenceAccountProductDetailgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceAccountProductDetail> res = null;
        Boolean checkType = true;
        try {

            res = evidenceDAO.EvidenceAccountProductDetailgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceAccountProductDetailgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceAccountHistory")
    public ResponseEntity EvidenceAccountHistory(@RequestBody EvidenceAccountHistoryReq req) {

        log.info("============= Start API EvidenceAccountHistory ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceAccountHistory> res = null;
        Boolean checkType = true;
        try {

            res = evidenceDAO.EvidenceAccountHistory(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceAccountHistory =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
