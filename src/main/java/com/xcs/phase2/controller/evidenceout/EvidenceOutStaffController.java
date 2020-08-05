package com.xcs.phase2.controller.evidenceout;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidenceout.EvidenceOutStaffDAO;
import com.xcs.phase2.model.evidenceout.EvidenceOutStaff;
import com.xcs.phase2.request.evidenceout.EvidenceOutStaffupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.evidenceout.EvidenceOutStaffinsAllResponse;
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
public class EvidenceOutStaffController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceOutStaffController.class);

    @Autowired
    private EvidenceOutStaffDAO evidenceOutStaffDAO;

    @PostMapping(value = "/EvidenceOutStaffinsAll")
    public ResponseEntity EvidenceOutStaffinsAll(@RequestBody List<EvidenceOutStaff> req) {

        log.info("============= Start API EvidenceOutStaffinsAll ================");
        EvidenceOutStaffinsAllResponse res = null;
        try {

            res = evidenceOutStaffDAO.EvidenceOutStaffinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API EvidenceOutStaffinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceOutStaffupdByCon")
    public ResponseEntity EvidenceOutStaffupdByCon(@RequestBody List<EvidenceOutStaff> req) {

        log.info("============= Start API EvidenceOutStaffupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(evidenceOutStaffDAO.EvidenceOutStaffupdByCon(req)){
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
        log.info("============= End API EvidenceOutStaffupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceOutStaffupdDelete")
    public ResponseEntity EvidenceOutStaffupdDelete(@RequestBody EvidenceOutStaffupdDeleteReq req) {

        log.info("============= Start API EvidenceOutStaffupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(evidenceOutStaffDAO.EvidenceOutStaffupdDelete(req)){
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
        log.info("============= End API EvidenceOutStaffupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
