package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductModelDAO;
import com.xcs.phase2.model.master.MasProductModel;
import com.xcs.phase2.request.master.MasProductModelgetByConReq;
import com.xcs.phase2.request.master.MasProductModelupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductModelinsAllResponse;
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
public class MasProductModelController {

    private static final Logger log = LoggerFactory.getLogger(MasProductModelController.class);

    @Autowired
    private MasProductModelDAO masProductModelDAO;

    @PostMapping(value = "/MasProductModelgetByCon")
    public ResponseEntity MasProductModelgetByCon(@RequestBody MasProductModelgetByConReq req) {

        log.info("============= Start API MasProductModelgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductModel> res = null;
        Boolean checkType = true;
        try {

            res = masProductModelDAO.MasProductModelgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductModelgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductModelinsAll")
    public ResponseEntity MasProductModelinsAll(@RequestBody MasProductModel req) {


        log.info("============= Start API MasProductModelinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductModelinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductModelDAO.MasProductModelinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductModelinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductModelupdAll")
    public ResponseEntity MasProductModelupdAll(@RequestBody MasProductModel req) {

        log.info("============= Start API MasProductModelupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductModelDAO.MasProductModelupdAll(req)) {
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
        log.info("============= End API MasProductModelupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductModelupdDelete")
    public ResponseEntity MasProductModelupdDelete(@RequestBody MasProductModelupdDeleteReq req) {

        log.info("============= Start API MasProductModelupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductModelDAO.MasProductModelupdDelete(req)) {
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
        log.info("============= End API MasProductModelupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
