package com.xcs.phase2.controller.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.investigate.InvestigateDetailStaffDAO;
import com.xcs.phase2.model.investigate.InvestigateDetailStaff;
import com.xcs.phase2.request.investigate.InvestigateDetailStaffupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.investigate.InvestigateDetailStaffinsAllResponse;
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
public class InvestigateDetailStaffController {

    private static final Logger log = LoggerFactory.getLogger(InvestigateDetailStaffController.class);

    @Autowired
    private InvestigateDetailStaffDAO investigateDetailStaffDAO;



    @PostMapping(value = "/InvestigateDetailStaffinsAll")
    public ResponseEntity InvestigateDetailStaffinsAll(@RequestBody List<InvestigateDetailStaff> req) {

        log.info("============= Start API InvestigateDetailStaffinsAll ================");
        InvestigateDetailStaffinsAllResponse res = null;
        try {

            res = investigateDetailStaffDAO.InvestigateDetailStaffinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API InvestigateDetailStaffinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailStaffupdByCon")
    public ResponseEntity InvestigateDetailStaffupdByCon(@RequestBody List<InvestigateDetailStaff> req) {

        log.info("============= Start API InvestigateDetailStaffupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(investigateDetailStaffDAO.InvestigateDetailStaffupdByCon(req)){
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
        log.info("============= End API InvestigateDetailStaffupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailStaffupdDelete")
    public ResponseEntity InvestigateDetailStaffupdDelete(@RequestBody List<InvestigateDetailStaffupdDeleteReq> req) {

        log.info("============= Start API InvestigateDetailStaffupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(investigateDetailStaffDAO.InvestigateDetailStaffupdDelete(req)){
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
        log.info("============= End API InvestigateDetailStaffupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
