package com.xcs.phase2.controller.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.EvidenceInBookDAO;
import com.xcs.phase2.model.evidencein.EvidenceInBook;
import com.xcs.phase2.request.evidencein.EvidenceInBookListgetByConAdvReq;
import com.xcs.phase2.request.evidencein.EvidenceInBookListgetByKeywordReq;
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
public class EvidenceInBookController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInBookController.class);

    @Autowired
    private EvidenceInBookDAO evidenceInBookDAO;

    @PostMapping(value = "/EvidenceInBookListgetByKeyword")
    public ResponseEntity EvidenceInBookListgetByKeyword(@RequestBody EvidenceInBookListgetByKeywordReq req) {

        log.info("============= Start API EvidenceInBookListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInBook> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInBookDAO.EvidenceInBookListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInBookListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInBookListgetByConAdv")
    public ResponseEntity EvidenceInBookListgetByConAdv(@RequestBody EvidenceInBookListgetByConAdvReq req) {

        log.info("============= Start API EvidenceInBookListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInBook> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInBookDAO.EvidenceInBookListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInBookListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
