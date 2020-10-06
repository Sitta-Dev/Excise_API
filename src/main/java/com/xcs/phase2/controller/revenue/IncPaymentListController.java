package com.xcs.phase2.controller.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.revenue.IncPaymentListDAO;
import com.xcs.phase2.model.revenue.RevenueIncPayment;
import com.xcs.phase2.request.revenue.IncPaymentListGetByConReq;
import com.xcs.phase2.request.revenue.IncPaymentListUpdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.revenue.IncPaymentListinsAllResponse;
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
public class IncPaymentListController {

    private static final Logger log = LoggerFactory.getLogger(IncPaymentListController.class);

    @Autowired
    private IncPaymentListDAO incPaymentListDAO;

    @PostMapping(value = "/IncPaymentListinsAll")
    public ResponseEntity IncPaymentListinsAll(@RequestBody RevenueIncPayment req) {

        log.info("============= Start API IncPaymentListinsAll ================");
        IncPaymentListinsAllResponse res = null;
        try {

            res = incPaymentListDAO.IncPaymentListinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API IncPaymentListinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/IncPaymentListGetByCon")
    public ResponseEntity IncPaymentListGetByCon(@RequestBody IncPaymentListGetByConReq req) {

        log.info("============= Start API IncPaymentListGetByCon ================");
        MessageResponse msg = new MessageResponse();
        RevenueIncPayment res = null;
        Boolean checkType = true;
        try {

            res = incPaymentListDAO.IncPaymentListGetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API IncPaymentListGetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/IncPaymentListUpdDelete")
    public ResponseEntity IncPaymentListUpdDelete(@RequestBody IncPaymentListUpdDeleteReq req) {

        log.info("============= Start API IncPaymentListUpdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(incPaymentListDAO.IncPaymentListUpdDelete(req)){
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
        log.info("============= End API IncPaymentListUpdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
