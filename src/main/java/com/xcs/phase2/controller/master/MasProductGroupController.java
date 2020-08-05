package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductGroupDAO;
import com.xcs.phase2.model.master.MasProductGroup;
import com.xcs.phase2.request.master.MasProductGroupgetByConReq;
import com.xcs.phase2.request.master.MasProductGroupupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductGroupinsAllResponse;
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
public class MasProductGroupController {

    private static final Logger log = LoggerFactory.getLogger(MasProductGroupController.class);

    @Autowired
    private MasProductGroupDAO masProductGroupDAO;

    @PostMapping(value = "/MasProductGroupgetByCon")
    public ResponseEntity MasProductGroupgetByCon(@RequestBody MasProductGroupgetByConReq req) {

        log.info("============= Start API MasProductGroupgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductGroup> res = null;
        Boolean checkType = true;
        try {

            res = masProductGroupDAO.MasProductGroupgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductGroupgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductGroupinsAll")
    public ResponseEntity MasProductGroupinsAll(@RequestBody MasProductGroup req) {


        log.info("============= Start API MasProductGroupinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductGroupinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductGroupDAO.MasProductGroupinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductGroupinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductGroupupdAll")
    public ResponseEntity MasProductGroupupdAll(@RequestBody MasProductGroup req) {

        log.info("============= Start API MasProductGroupupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductGroupDAO.MasProductGroupupdAll(req)) {
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
        log.info("============= End API MasProductGroupupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductGroupupdDelete")
    public ResponseEntity MasProductGroupupdDelete(@RequestBody MasProductGroupupdDeleteReq req) {

        log.info("============= Start API MasProductGroupupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductGroupDAO.MasProductGroupupdDelete(req)) {
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
        log.info("============= End API MasProductGroupupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
