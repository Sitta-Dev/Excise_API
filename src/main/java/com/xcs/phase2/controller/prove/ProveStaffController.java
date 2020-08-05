package com.xcs.phase2.controller.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveStaffDAO;
import com.xcs.phase2.model.prove.ProveStaff;
import com.xcs.phase2.request.prove.ProveStaffgetByConReq;
import com.xcs.phase2.request.prove.ProveStaffupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.prove.ProveStaffinsAllResponse;
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
public class ProveStaffController {

    private static final Logger log = LoggerFactory.getLogger(ProveStaffController.class);

    @Autowired
    private ProveStaffDAO proveStaffDAO;

    @PostMapping(value = "/ProveStaffgetByCon")
    public ResponseEntity ProveStaffgetByCon(@RequestBody ProveStaffgetByConReq req) {

        log.info("============= Start API ProveStaffgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<ProveStaff> res = null;
        Boolean checkType = true;
        try {

            res = proveStaffDAO.ProveStaffgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveStaffgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveStaffinsAll")
    public ResponseEntity ProveStaffinsAll(@RequestBody ProveStaff req) {

        log.info("============= Start API ProveStaffinsAll ================");
        ProveStaffinsAllResponse res = null;
        try {

            res = proveStaffDAO.ProveStaffinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API ProveStaffinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveStaffupdByCon")
    public ResponseEntity ProveStaffupdByCon(@RequestBody ProveStaff req) {

        log.info("============= Start API ProveStaffupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveStaffDAO.ProveStaffupdByCon(req)){
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
        log.info("============= End API ProveStaffupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveStaffupdDelete")
    public ResponseEntity ProveStaffupdDelete(@RequestBody ProveStaffupdDeleteReq req) {

        log.info("============= Start API ProveStaffupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveStaffDAO.ProveStaffupdDelete(req)){
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
        log.info("============= End API ProveStaffupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
