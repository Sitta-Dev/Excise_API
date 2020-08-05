package com.xcs.phase2.controller.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.revenue.RevenueListDAO;
import com.xcs.phase2.model.revenue.NewRevenueList;
import com.xcs.phase2.model.revenue.RevenueList;
import com.xcs.phase2.request.revenue.*;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.revenue.RevenueinsAllResponse;
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
public class RevenueListController {

    private static final Logger log = LoggerFactory.getLogger(RevenueListController.class);

    @Autowired
    private RevenueListDAO revenueListDAO;

    @PostMapping(value = "/RevenueListgetByKeyword")
    public ResponseEntity RevenueListgetByKeyword(@RequestBody RevenuegetByKeywordReq req) {

        log.info("============= Start API RevenueListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<NewRevenueList> res = null;
        Boolean checkType = true;
        try {

            res = revenueListDAO.RevenueListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenueListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueListgetByConAdv")
    public ResponseEntity RevenueListgetByConAdv(@RequestBody RevenuegetByConAdvReq req) {

        log.info("============= Start API RevenueListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<NewRevenueList> res = null;
        Boolean checkType = true;
        try {

            res = revenueListDAO.RevenueListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenueListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenuegetByCon")
    public ResponseEntity RevenuegetByCon(@RequestBody RevenuegetByConReq req) {


        log.info("============= Start API RevenuegetByCon ================");
        MessageResponse msg = new MessageResponse();
        RevenueList res = null;
        Boolean checkType = true;
        try {

            res = revenueListDAO.RevenuegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenuegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueinsAll")
    public ResponseEntity RevenueinsAll(@RequestBody RevenueList req) {

        log.info("============= Start API RevenueinsAll ================");
        RevenueinsAllResponse res = null;
        try {

            res = revenueListDAO.RevenueinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API RevenueinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueupdByCon")
    public ResponseEntity RevenueupdByCon(@RequestBody RevenueList req) {

        log.info("============= Start API RevenueupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueListDAO.RevenueupdByCon(req)){
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
        log.info("============= End API RevenueupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueupdDelete")
    public ResponseEntity RevenueupdDelete(@RequestBody RevenueupdDeleteReq req) {

        log.info("============= Start API RevenueupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueListDAO.RevenueupdDelete(req)){
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
        log.info("============= End API RevenueupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenuePaymentupdByCon")
    public ResponseEntity RevenuePaymentupdByCon(@RequestBody RevenuePaymentupdByConReq req) {

        log.info("============= Start API RevenuePaymentupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueListDAO.RevenuePaymentupdByCon(req)){
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
        log.info("============= End API RevenuePaymentupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
