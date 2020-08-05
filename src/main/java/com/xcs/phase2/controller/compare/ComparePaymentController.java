package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.ComparePaymentDAO;
import com.xcs.phase2.model.compare.ComparePayment;
import com.xcs.phase2.request.compare.ComparePaymentupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.compare.ComparePaymentinsAllResponse;
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
public class ComparePaymentController {

    private static final Logger log = LoggerFactory.getLogger(CompareDetailStaffController.class);

    @Autowired
    private ComparePaymentDAO comparePaymentDAO;

    @PostMapping(value = "/ComparePaymentinsAll")
    public ResponseEntity ComparePaymentinsAll(@RequestBody List<ComparePayment> req) {

        log.info("============= Start API ComparePaymentinsAll ================");
        ComparePaymentinsAllResponse res = null;
        try {

            res = comparePaymentDAO.ComparePaymentinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API ComparePaymentinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/ComparePaymentupdDelete")
    public ResponseEntity ComparePaymentupdDelete(@RequestBody List<ComparePaymentupdDeleteReq> req) {

        log.info("============= Start API ComparePaymentupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(comparePaymentDAO.ComparePaymentupdDelete(req)){
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
        log.info("============= End API ComparePaymentupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
