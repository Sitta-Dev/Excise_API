package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductSubTypeDAO;
import com.xcs.phase2.model.master.MasProductSubType;
import com.xcs.phase2.request.master.MasProductSubTypegetByConReq;
import com.xcs.phase2.request.master.MasProductSubTypeupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductSubTypeinsAllResponse;
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
public class MasProductSubTypeController {

    private static final Logger log = LoggerFactory.getLogger(MasProductSubTypeController.class);

    @Autowired
    private MasProductSubTypeDAO masProductSubTypeDAO;

    @PostMapping(value = "/MasProductSubTypegetByCon")
    public ResponseEntity MasProductSubTypegetByCon(@RequestBody MasProductSubTypegetByConReq req) {

        log.info("============= Start API MasProductSubTypegetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductSubType> res = null;
        Boolean checkType = true;
        try {

            res = masProductSubTypeDAO.MasProductSubTypegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSubTypegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSubTypeinsAll")
    public ResponseEntity MasProductSubTypeinsAll(@RequestBody MasProductSubType req) {


        log.info("============= Start API MasProductSubTypeinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductSubTypeinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductSubTypeDAO.MasProductSubTypeinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSubTypeinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSubTypeupdAll")
    public ResponseEntity MasProductSubTypeupdAll(@RequestBody MasProductSubType req) {

        log.info("============= Start API MasProductSubTypeupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductSubTypeDAO.MasProductSubTypeupdAll(req)) {
                msg.setIsSuccess(Message.TRUE);
                msg.setMsg(Message.COMPLETE);
            } else {
                msg.setIsSuccess(Message.FALSE);
                msg.setMsg(Message.NOT_COMPLETE);
            }

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API MasProductSubTypeupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSubTypeupdDelete")
    public ResponseEntity MasProductSubTypeupdDelete(@RequestBody MasProductSubTypeupdDeleteReq req) {

        log.info("============= Start API MasProductSubTypeupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductSubTypeDAO.MasProductSubTypeupdDelete(req)) {
                msg.setIsSuccess(Message.TRUE);
                msg.setMsg(Message.COMPLETE);
            } else {
                msg.setIsSuccess(Message.FALSE);
                msg.setMsg(Message.NOT_COMPLETE);
            }

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API MasProductSubTypeupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
