package com.xcs.phase2.controller.evidencein;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.EvidenceInStaffDAO;
import com.xcs.phase2.model.evidencein.EvidenceInStaff;
import com.xcs.phase2.request.evidencein.EvidenceInStaffgetByConReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.evidencein.EvidenceInStaffinsAllResponse;
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
public class EvidenceInStaffController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceInStaffController.class);

    @Autowired
    private EvidenceInStaffDAO evidenceInStaffDAO;

    @PostMapping(value = "/EvidenceInStaffinsAll")
    public ResponseEntity EvidenceInStaffinsAll(@RequestBody List<EvidenceInStaff> req) {

        log.info("============= Start API EvidenceInStaffinsAll ================");
        EvidenceInStaffinsAllResponse res = null;
        try {

            res = evidenceInStaffDAO.EvidenceInStaffinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API EvidenceInStaffinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInStaffupdByCon")
    public ResponseEntity EvidenceInStaffupdByCon(@RequestBody List<EvidenceInStaff> req) {

        log.info("============= Start API EvidenceInStaffupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(evidenceInStaffDAO.EvidenceInStaffupdByCon(req)){
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
        log.info("============= End API EvidenceInStaffupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EvidenceInStaffgetByCon")
    public ResponseEntity EvidenceInStaffgetByCon(@RequestBody EvidenceInStaffgetByConReq req) {

        log.info("============= Start API EvidenceInStaffgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<EvidenceInStaff> res = null;
        Boolean checkType = true;
        try {

            res = evidenceInStaffDAO.EvidenceInStaffgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EvidenceInStaffgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
