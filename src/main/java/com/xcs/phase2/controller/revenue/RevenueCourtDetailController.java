package com.xcs.phase2.controller.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.revenue.RevenueCourtDetailDAO;
import com.xcs.phase2.model.revenue.NewRevenueCourtDetail;
import com.xcs.phase2.request.revenue.RevenueCourtDetailDeleteReq;
import com.xcs.phase2.request.revenue.RevenueCourtDetailgetByCreateReq;
import com.xcs.phase2.request.revenue.RevenueCourtDetailupdByConReq;
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
public class RevenueCourtDetailController {

    private static final Logger log = LoggerFactory.getLogger(RevenueCourtDetailController.class);

    @Autowired
    private RevenueCourtDetailDAO revenueCourtDetailDAO;

    @PostMapping(value = "/RevenueCourtDetailgetByCreate")
    public ResponseEntity RevenueCourtDetailgetByCreate(@RequestBody RevenueCourtDetailgetByCreateReq req) {

        log.info("============= Start API RevenueCourtDetailgetByCreate ================");
        MessageResponse msg = new MessageResponse();
        List<NewRevenueCourtDetail> res = null;
        Boolean checkType = true;
        try {

            res = revenueCourtDetailDAO.RevenueCourtDetailgetByCreate(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenueCourtDetailgetByCreate =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueCourtDetailupdByCon")
    public ResponseEntity RevenueCourtDetailupdByCon(@RequestBody RevenueCourtDetailupdByConReq req) {

        log.info("============= Start API RevenueCourtDetailupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueCourtDetailDAO.RevenueCourtDetailupdByCon(req)){
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
        log.info("============= End API RevenueCourtDetailupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueCourtDetailDelete")
    public ResponseEntity RevenueCourtDetailDelete(@RequestBody RevenueCourtDetailDeleteReq req) {

        log.info("============= Start API RevenueCourtDetailDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueCourtDetailDAO.RevenueCourtDetailDelete(req)){
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
        log.info("============= End API RevenueCourtDetailDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
