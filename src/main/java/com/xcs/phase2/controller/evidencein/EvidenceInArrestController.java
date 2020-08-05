package com.xcs.phase2.controller.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.EvidenceInArrestDAO;
import com.xcs.phase2.model.evidencein.EvidenceInArrest;
import com.xcs.phase2.request.evidencein.EvidenceInArrestgetByProveIDReq;
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
public class EvidenceInArrestController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInArrestController.class);

    @Autowired
    private EvidenceInArrestDAO evidenceInArrestDAO;

    @PostMapping(value = "/EvidenceInArrestgetByProveID")
    public ResponseEntity EvidenceInArrestgetByProveID(@RequestBody EvidenceInArrestgetByProveIDReq req) {

        log.info("============= Start API EvidenceInArrestgetByProveID ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInArrest> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInArrestDAO.EvidenceInArrestgetByProveID(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInArrestgetByProveID =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }


}
