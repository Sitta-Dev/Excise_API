package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductSizeDAO;
import com.xcs.phase2.model.master.MasProductSize;
import com.xcs.phase2.request.master.MasProductSizegetByConAdvReq;
import com.xcs.phase2.request.master.MasProductSizegetByConReq;
import com.xcs.phase2.request.master.MasProductSizegetByKeywordReq;
import com.xcs.phase2.request.master.MasProductSizeupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductSizeinsAllResponse;
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
public class MasProductSizeController {

    private static final Logger log = LoggerFactory.getLogger(MasProductSizeController.class);

    @Autowired
    private MasProductSizeDAO masProductSizeDAO;

    @PostMapping(value = "/MasProductSizegetByKeyword")
    public ResponseEntity MasProductSizegetByKeyword(@RequestBody MasProductSizegetByKeywordReq req) {

        log.info("============= Start API MasProductSizegetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductSize> res = null;
        Boolean checkType = true;
        try {

            res = masProductSizeDAO.MasProductSizegetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSizegetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSizegetByConAdv")
    public ResponseEntity MasProductSizegetByConAdv(@RequestBody MasProductSizegetByConAdvReq req) {

        log.info("============= Start API MasProductSizegetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductSize> res = null;
        Boolean checkType = true;
        try {

            res = masProductSizeDAO.MasProductSizegetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSizegetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSizegetByCon")
    public ResponseEntity MasProductSizegetByCon(@RequestBody MasProductSizegetByConReq req) {

        log.info("============= Start API MasProductSizegetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasProductSize res = null;
        Boolean checkType = true;
        try {

            res = masProductSizeDAO.MasProductSizegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSizegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSizeinsAll")
    public ResponseEntity MasProductSizeinsAll(@RequestBody MasProductSize req) {


        log.info("============= Start API MasProductSizeinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductSizeinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductSizeDAO.MasProductSizeinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSizeinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSizeupdAll")
    public ResponseEntity MasProductSizeupdAll(@RequestBody MasProductSize req) {

        log.info("============= Start API MasProductSizeupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductSizeDAO.MasProductSizeupdAll(req)) {
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
        log.info("============= End API MasProductSizeupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSizeupdDelete")
    public ResponseEntity MasProductSizeupdDelete(@RequestBody MasProductSizeupdDeleteReq req) {

        log.info("============= Start API MasProductSizeupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductSizeDAO.MasProductSizeupdDelete(req)) {
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
        log.info("============= End API MasProductSizeupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
