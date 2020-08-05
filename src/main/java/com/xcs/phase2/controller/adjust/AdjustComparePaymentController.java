package com.xcs.phase2.controller.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.adjust.AdjustComparePaymentDAO;
import com.xcs.phase2.model.adjust.AdjustComparePayment;
import com.xcs.phase2.request.adjust.AdjustComparePaymentupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.adjust.AdjustComparePaymentinsAllResponse;
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
public class AdjustComparePaymentController {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareDetailStaffController.class);

    @Autowired
    private AdjustComparePaymentDAO comparePaymentDAO;

    @PostMapping(value = "/AdjustComparePaymentinsAll")
    public ResponseEntity AdjustComparePaymentinsAll(@RequestBody List<AdjustComparePayment> req) {

        log.info("============= Start API AdjustComparePaymentinsAll ================");
        AdjustComparePaymentinsAllResponse res = null;
        try {

            res = comparePaymentDAO.AdjustComparePaymentinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API AdjustComparePaymentinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustComparePaymentupdDelete")
    public ResponseEntity AdjustComparePaymentupdDelete(@RequestBody List<AdjustComparePaymentupdDeleteReq> req) {

        log.info("============= Start API AdjustComparePaymentupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(comparePaymentDAO.AdjustComparePaymentupdDelete(req)){
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
        log.info("============= End API AdjustComparePaymentupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
