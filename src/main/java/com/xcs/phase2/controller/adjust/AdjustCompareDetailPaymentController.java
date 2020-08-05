package com.xcs.phase2.controller.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.adjust.AdjustCompareDetailPaymentDAO;
import com.xcs.phase2.model.adjust.AdjustCompareDetailPayment;
import com.xcs.phase2.request.adjust.AdjustCompareDetailPaymentupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.adjust.AdjustCompareDetailPaymentinsAllResponse;
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
public class AdjustCompareDetailPaymentController {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareDetailPaymentController.class);

    @Autowired
    private AdjustCompareDetailPaymentDAO adjustCompareDetailPaymentDAO;

    @PostMapping(value = "/AdjustCompareDetailPaymentinsAll")
    public ResponseEntity AdjustCompareDetailPaymentinsAll(@RequestBody List<AdjustCompareDetailPayment> req) {

        log.info("============= Start API AdjustCompareDetailPaymentinsAll ================");
        AdjustCompareDetailPaymentinsAllResponse res = null;
        try {

            res = adjustCompareDetailPaymentDAO.AdjustCompareDetailPaymentinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API AdjustCompareDetailPaymentinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareDetailPaymentupdByCon")
    public ResponseEntity AdjustCompareDetailPaymentupdByCon(@RequestBody List<AdjustCompareDetailPayment> req) {

        log.info("============= Start API AdjustCompareDetailPaymentupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(adjustCompareDetailPaymentDAO.AdjustCompareDetailPaymentupdByCon(req)){
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
        log.info("============= End API AdjustCompareDetailPaymentupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareDetailPaymentupdDelete")
    public ResponseEntity AdjustCompareDetailPaymentupdDelete(@RequestBody List<AdjustCompareDetailPaymentupdDeleteReq> req) {

        log.info("============= Start API AdjustCompareDetailPaymentupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(adjustCompareDetailPaymentDAO.AdjustCompareDetailPaymentupdDelete(req)){
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
        log.info("============= End API AdjustCompareDetailPaymentupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
