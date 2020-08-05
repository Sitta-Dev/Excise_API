package com.xcs.phase2.controller.target;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.target.TargetDAO;
import com.xcs.phase2.model.target.Target;
import com.xcs.phase2.model.target.TargetgetByCon;
import com.xcs.phase2.request.target.IsSentTargetupdByConReq;
import com.xcs.phase2.request.target.TargetgetByConReq;
import com.xcs.phase2.request.target.TargetupdByConReq;
import com.xcs.phase2.request.target.TargetupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.target.TargetinsAllResponse;
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
public class TargetController {

    private static final Logger log = LoggerFactory.getLogger(TargetController.class);

    @Autowired
    private TargetDAO targetDAO;

    @PostMapping(value = "/TargetgetByCon")
    public ResponseEntity TargetgetByCon(@RequestBody TargetgetByConReq req) {

        log.info("============= Start API TargetgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<TargetgetByCon> res = null;
        Boolean checkType = true;
        try {

            res = targetDAO.TargetgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API TargetgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/TargetinsAll")
    public ResponseEntity TargetinsAll(@RequestBody Target req) {

        log.info("============= Start API TargetinsAll ================");
        TargetinsAllResponse res = null;
        try {

            res = targetDAO.TargetinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API TargetinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/TargetupdByCon")
    public ResponseEntity TargetupdByCon(@RequestBody List<TargetupdByConReq> req) {

        log.info("============= Start API TargetupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(targetDAO.TargetupdByCon(req)){
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
        log.info("============= End API TargetupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/IsSentTargetupdByCon")
    public ResponseEntity IsSentTargetupdByCon(@RequestBody IsSentTargetupdByConReq req) {

        log.info("============= Start API IsSentTargetupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(targetDAO.IsSentTargetupdByCon(req)){
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
        log.info("============= End API IsSentTargetupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/TargetupdDelete")
    public ResponseEntity TargetupdDelete(@RequestBody TargetupdDeleteReq req) {

        log.info("============= Start API TargetupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(targetDAO.TargetupdDelete(req)){
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
        log.info("============= End API TargetupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
