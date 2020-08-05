package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.CompareDetailPaymentDAO;
import com.xcs.phase2.model.compare.CompareDetailPayment;
import com.xcs.phase2.request.compare.CompareDetailPaymentupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.compare.CompareDetailPaymentinsAllResponse;
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
public class CompareDetailPaymentController {

    private static final Logger log = LoggerFactory.getLogger(CompareDetailPaymentController.class);

    @Autowired
    private CompareDetailPaymentDAO compareDetailPaymentDAO;

    @PostMapping(value = "/CompareDetailPaymentinsAll")
    public ResponseEntity CompareDetailPaymentinsAll(@RequestBody List<CompareDetailPayment> req) {

        log.info("============= Start API CompareDetailPaymentinsAll ================");
        CompareDetailPaymentinsAllResponse res = null;
        try {

            res = compareDetailPaymentDAO.CompareDetailPaymentinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API CompareDetailPaymentinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareDetailPaymentupdByCon")
    public ResponseEntity CompareDetailPaymentupdByCon(@RequestBody List<CompareDetailPayment> req) {

        log.info("============= Start API CompareDetailPaymentupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailPaymentDAO.CompareDetailPaymentupdByCon(req)){
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
        log.info("============= End API CompareDetailPaymentupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareDetailPaymentupdDelete")
    public ResponseEntity CompareDetailPaymentupdDelete(@RequestBody List<CompareDetailPaymentupdDeleteReq> req) {

        log.info("============= Start API CompareDetailPaymentupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailPaymentDAO.CompareDetailPaymentupdDelete(req)){
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
        log.info("============= End API CompareDetailPaymentupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
