package com.xcs.phase2.controller.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.EvidenceInOutItemDAO;
import com.xcs.phase2.model.evidencein.EvidenceInOutItem;
import com.xcs.phase2.request.evidencein.EvidenceInOutItemupdDeleteIsReturnReq;
import com.xcs.phase2.request.evidencein.EvidenceInOutItemupdIsReturnReq;
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
public class EvidenceInOutItemController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInOutItem.class);

    @Autowired
    private EvidenceInOutItemDAO evidenceInOutItemDAO;

    @PostMapping(value = "/EvidenceInOutItemupdIsReturn")
    public ResponseEntity EvidenceInOutItemupdIsReturn(@RequestBody List<EvidenceInOutItemupdIsReturnReq> req) {

        log.info("============= Start API EvidenceInOutItemupdIsReturn ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (evidenceInOutItemDAO.EvidenceInOutItemupdIsReturn(req)) {
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
        log.info("============= End API EvidenceInOutItemupdIsReturn =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInOutItemupdDeleteIsReturn")
    public ResponseEntity EvidenceInOutItemupdDeleteIsReturn(@RequestBody List<EvidenceInOutItemupdDeleteIsReturnReq> req) {

        log.info("============= Start API EvidenceInOutItemupdDeleteIsReturn ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (evidenceInOutItemDAO.EvidenceInOutItemupdDeleteIsReturn(req)) {
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
        log.info("============= End API EvidenceInOutItemupdDeleteIsReturn =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
