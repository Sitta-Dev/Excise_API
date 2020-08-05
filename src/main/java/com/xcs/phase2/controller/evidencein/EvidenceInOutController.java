package com.xcs.phase2.controller.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.EvidenceInOutDAO;
import com.xcs.phase2.model.evidencein.EvidenceInOut;
import com.xcs.phase2.request.evidencein.EvidenceInOutgetByWarehouseIDReq;
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
public class EvidenceInOutController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInOutController.class);

    @Autowired
    private EvidenceInOutDAO evidenceInOutDAO;

    @PostMapping(value = "/EvidenceInOutgetByWarehouseID")
    public ResponseEntity EvidenceInOutgetByWarehouseID(@RequestBody EvidenceInOutgetByWarehouseIDReq req) {

        log.info("============= Start API EvidenceInOutgetByWarehouseID ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInOut> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInOutDAO.EvidenceInOutgetByWarehouseID(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInOutgetByWarehouseID =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
