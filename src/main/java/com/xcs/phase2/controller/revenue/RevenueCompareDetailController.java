package com.xcs.phase2.controller.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.revenue.RevenueCompareDetailDAO;
import com.xcs.phase2.model.revenue.RevenueCompareDetail;
import com.xcs.phase2.request.revenue.RevenueCompareDetailReceiptupdByConReq;
import com.xcs.phase2.request.revenue.RevenueCompareDetailReceiptupdDeleteReq;
import com.xcs.phase2.request.revenue.RevenueComparegetByCompareReceiptIDReq;
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
public class RevenueCompareDetailController {

    private static final Logger log = LoggerFactory.getLogger(RevenueCompareDetailController.class);

    @Autowired
    private RevenueCompareDetailDAO revenueCompareDetailDAO;

    @PostMapping(value = "/RevenueComparegetByCompareReceiptID")
    public ResponseEntity RevenueComparegetByCompareReceiptID(@RequestBody RevenueComparegetByCompareReceiptIDReq req) {

        log.info("============= Start API RevenueComparegetByCompareReceiptID ================");
        MessageResponse msg = new MessageResponse();
        List<RevenueCompareDetail> res = null;
        Boolean checkType = true;
        try {

            res = revenueCompareDetailDAO.RevenueComparegetByCompareReceiptID(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenueComparegetByCompareReceiptID =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueCompareDetailReceiptupdByCon")
    public ResponseEntity RevenueCompareDetailReceiptupdByCon(@RequestBody RevenueCompareDetailReceiptupdByConReq req) {

        log.info("============= Start API RevenueCompareDetailReceiptupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueCompareDetailDAO.RevenueCompareDetailReceiptupdByCon(req)){
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
        log.info("============= End API RevenueCompareDetailReceiptupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueCompareDetailReceiptupdDelete")
    public ResponseEntity RevenueCompareDetailReceiptupdDelete(@RequestBody RevenueCompareDetailReceiptupdDeleteReq req) {

        log.info("============= Start API RevenueCompareDetailReceiptupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueCompareDetailDAO.RevenueCompareDetailReceiptupdDelete(req)){
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
        log.info("============= End API RevenueCompareDetailReceiptupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
