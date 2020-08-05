package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.TransactionRunningDAO;
import com.xcs.phase2.model.compare.TransactionRunning;
import com.xcs.phase2.request.compare.TransactionRunninggetByConReq;
import com.xcs.phase2.request.compare.TransactionRunningupdByConReq;
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
public class TransactionRunningController {

    private static final Logger log = LoggerFactory.getLogger(TransactionRunningController.class);

    @Autowired
    private TransactionRunningDAO transactionRunningDAO;

    @PostMapping(value = "/TransactionRunninggetByCon")
    public ResponseEntity TransactionRunninggetByCon(@RequestBody TransactionRunninggetByConReq req) {

        log.info("============= Start API TransactionRunninggetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<TransactionRunning> res = null;
        Boolean checkType = true;
        try {

            res = transactionRunningDAO.TransactionRunninggetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API TransactionRunninggetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/TransactionRunninginsAll")
    public ResponseEntity TransactionRunninginsAll(@RequestBody TransactionRunning req) {

        log.info("============= Start API TransactionRunninginsAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(transactionRunningDAO.TransactionRunninginsAll(req)){
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
        log.info("============= End API TransactionRunninginsAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/TransactionRunningupdByCon")
    public ResponseEntity TransactionRunningupdByCon(@RequestBody TransactionRunningupdByConReq req) {

        log.info("============= Start API TransactionRunningupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(transactionRunningDAO.TransactionRunningupdByCon(req)){
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
        log.info("============= End API TransactionRunningupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
