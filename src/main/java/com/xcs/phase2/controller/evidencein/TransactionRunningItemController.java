package com.xcs.phase2.controller.evidencein;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.evidencein.TransactionRunningItemDAO;
import com.xcs.phase2.model.evidencein.TransactionRunningItem;
import com.xcs.phase2.request.evidencein.TransactionRunningItemgetByConReq;
import com.xcs.phase2.request.evidencein.TransactionRunningItemupdByConReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.evidencein.TransactionRunningIteminsAllResponse;
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
public class TransactionRunningItemController {

    private static final Logger log = LoggerFactory.getLogger(TransactionRunningItemController.class);

    @Autowired
    private TransactionRunningItemDAO transactionRunningItemDAO;

    @PostMapping(value = "/TransactionRunningItemgetByCon")
    public ResponseEntity TransactionRunningItemgetByCon(@RequestBody TransactionRunningItemgetByConReq req) {

        log.info("============= Start API TransactionRunningItemgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<TransactionRunningItem> res = null;
        Boolean checkType = true;
        try {

            res = transactionRunningItemDAO.TransactionRunningItemgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API TransactionRunningItemgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/TransactionRunningIteminsAll")
    public ResponseEntity TransactionRunningIteminsAll(@RequestBody TransactionRunningItem req) {

        log.info("============= Start API TransactionRunningIteminsAll ================");
        TransactionRunningIteminsAllResponse res = null;
        try {

            res = transactionRunningItemDAO.TransactionRunningIteminsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API TransactionRunningIteminsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/TransactionRunningItemupdByCon")
    public ResponseEntity TransactionRunningItemupdByCon(@RequestBody TransactionRunningItemupdByConReq req) {

        log.info("============= Start API TransactionRunningItemupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(transactionRunningItemDAO.TransactionRunningItemupdByCon(req)){
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
        log.info("============= End API TransactionRunningItemupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
