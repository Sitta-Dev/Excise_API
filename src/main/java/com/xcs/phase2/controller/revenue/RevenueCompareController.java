package com.xcs.phase2.controller.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.revenue.RevenueCompareDAO;
import com.xcs.phase2.model.revenue.NewRevenueCourt;
import com.xcs.phase2.model.revenue.RevenueCompare;
import com.xcs.phase2.request.revenue.*;
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
public class RevenueCompareController {

    private static final Logger log = LoggerFactory.getLogger(RevenueCompareController.class);

    @Autowired
    private RevenueCompareDAO revenueCompareDAO;

    @PostMapping(value = "/RevenueComparegetByCreate")
    public ResponseEntity RevenueComparegetByCreate(@RequestBody RevenueComparegetByCreateReq req) {

        log.info("============= Start API RevenueComparegetByCreate ================");
        MessageResponse msg = new MessageResponse();
        List<RevenueCompare> res = null;
        Boolean checkType = true;
        try {

            res = revenueCompareDAO.RevenueComparegetByCreate(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenueComparegetByCreate =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueComparegetByCon")
    public ResponseEntity RevenueComparegetByCon(@RequestBody RevenueComparegetByConReq req) {

        log.info("============= Start API RevenueComparegetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<RevenueCompare> res = null;
        Boolean checkType = true;
        try {

            res = revenueCompareDAO.RevenueComparegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenueComparegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueCourtgetByCon")
    public ResponseEntity RevenueCourtgetByCon(@RequestBody RevenueCourtgetByConReq req) {

        log.info("============= Start API RevenueCourtgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<NewRevenueCourt> res = null;
        Boolean checkType = true;
        try {

            res = revenueCompareDAO.RevenueCourtgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenueCourtgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueCourtupdStatus")
    public ResponseEntity RevenueCourtupdStatus(@RequestBody RevenueCourtupdStatusReq req) {

        log.info("============= Start API RevenueCourtupdStatus ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueCompareDAO.RevenueCourtupdStatus(req)){
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
        log.info("============= End API RevenueCourtupdStatus =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueCompareStatus")
    public ResponseEntity RevenueCompareStatus(@RequestBody RevenueCompareStatusReq req) {

        log.info("============= Start API RevenueCompareStatus ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueCompareDAO.RevenueCompareStatus(req)){
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
        log.info("============= End API RevenueCompareStatus =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueReturnupdREFno")
    public ResponseEntity RevenueReturnupdREFno(@RequestBody RevenueReturnupdREFnoReq req) {

        log.info("============= Start API RevenueReturnupdREFno ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueCompareDAO.RevenueReturnupdREFno(req)){
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
        log.info("============= End API RevenueReturnupdREFno =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
