package com.xcs.phase2.controller.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.EvidenceInListDAO;
import com.xcs.phase2.model.evidencein.EvidenceInList;
import com.xcs.phase2.request.evidencein.EvidenceInListgetByConAdvReq;
import com.xcs.phase2.request.evidencein.EvidenceInListgetByKeywordReq;
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
public class EvidenceInListController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInListController.class);

    @Autowired
    private EvidenceInListDAO evidenceInListDAO;

    @PostMapping(value = "/EvidenceInListgetByKeyword")
    public ResponseEntity EvidenceInListgetByKeyword(@RequestBody EvidenceInListgetByKeywordReq req) {

        log.info("============= Start API EvidenceInListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInList> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInListDAO.EvidenceInListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInListgetByConAdv")
    public ResponseEntity EvidenceInListgetByConAdv(@RequestBody EvidenceInListgetByConAdvReq req) {

        log.info("============= Start API EvidenceInListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInList> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInListDAO.EvidenceInListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
