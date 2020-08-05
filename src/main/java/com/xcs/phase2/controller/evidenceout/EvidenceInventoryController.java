package com.xcs.phase2.controller.evidenceout;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidenceout.EvidenceInventoryDAO;
import com.xcs.phase2.model.evidenceout.EvidenceInventory;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByConDetailReq;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByConReq;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByEvidenceInItemCodeReq;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByOfficeNameReq;
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
public class EvidenceInventoryController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInventoryController.class);

    @Autowired
    private EvidenceInventoryDAO evidenceInventoryDAO;

    @PostMapping(value = "/EvidenceInventoryListgetByOfficeName")
    public ResponseEntity EvidenceInventoryListgetByOfficeName(@RequestBody EvidenceInventoryListgetByOfficeNameReq req) {

        log.info("============= Start API EvidenceInventoryListgetByOfficeName ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInventory> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInventoryDAO.EvidenceInventoryListgetByOfficeName(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInventoryListgetByOfficeName =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInventoryListgetByEvidenceInItemCode")
    public ResponseEntity EvidenceInventoryListgetByEvidenceInItemCode(@RequestBody EvidenceInventoryListgetByEvidenceInItemCodeReq req) {

        log.info("============= Start API EvidenceInventoryListgetByEvidenceInItemCode ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInventory> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInventoryDAO.EvidenceInventoryListgetByEvidenceInItemCode(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInventoryListgetByEvidenceInItemCode =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInventoryListgetByCon")
    public ResponseEntity EvidenceInventoryListgetByCon(@RequestBody EvidenceInventoryListgetByConReq req) {

        log.info("============= Start API EvidenceInventoryListgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInventory> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInventoryDAO.EvidenceInventoryListgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInventoryListgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInventoryListgetByConDetail")
    public ResponseEntity EvidenceInventoryListgetByConDetail(@RequestBody EvidenceInventoryListgetByConDetailReq req) {

        log.info("============= Start API EvidenceInventoryListgetByConDetail ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInventory> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInventoryDAO.EvidenceInventoryListgetByConDetail(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInventoryListgetByConDetail =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
