package com.xcs.phase2.controller.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.EvidenceInItemDAO;
import com.xcs.phase2.model.evidencein.EvidenceInItem;
import com.xcs.phase2.request.evidencein.EvidenceInItemupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.evidencein.EvidenceInIteminsAllResponse;
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
public class EvidenceInItemController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInItemController.class);

    @Autowired
    private EvidenceInItemDAO evidenceInItemDAO;

    @PostMapping(value = "/EvidenceInIteminsAll")
    public ResponseEntity EvidenceInIteminsAll(@RequestBody List<EvidenceInItem> req) {

        log.info("============= Start API EvidenceInIteminsAll ================");
        EvidenceInIteminsAllResponse res = null;
        try {

            res = evidenceInItemDAO.EvidenceInIteminsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API EvidenceInIteminsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInItemupdByCon")
    public ResponseEntity EvidenceInItemupdByCon(@RequestBody List<EvidenceInItem> req) {

        log.info("============= Start API EvidenceInItemupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (evidenceInItemDAO.EvidenceInItemupdByCon(req)) {
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
        log.info("============= End API EvidenceInItemupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInItemupdDelete")
    public ResponseEntity EvidenceInItemupdDelete(@RequestBody List<EvidenceInItemupdDeleteReq> req) {

        log.info("============= Start API EvidenceInItemupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (evidenceInItemDAO.EvidenceInItemupdDelete(req)) {
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
        log.info("============= End API EvidenceInItemupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
