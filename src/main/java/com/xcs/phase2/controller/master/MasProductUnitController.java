package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductUnitDAO;
import com.xcs.phase2.model.master.MasProductUnit;
import com.xcs.phase2.request.master.MasProductUnitgetByConAdvReq;
import com.xcs.phase2.request.master.MasProductUnitgetByConReq;
import com.xcs.phase2.request.master.MasProductUnitgetByKeywordReq;
import com.xcs.phase2.request.master.MasProductUnitupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductUnitinsAllResponse;
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
public class MasProductUnitController {

    private static final Logger log = LoggerFactory.getLogger(MasProductUnitController.class);

    @Autowired
    private MasProductUnitDAO masProductUnitDAO;

    @PostMapping(value = "/MasProductUnitgetByKeyword")
    public ResponseEntity MasProductUnitgetByKeyword(@RequestBody MasProductUnitgetByKeywordReq req) {

        log.info("============= Start API MasProductUnitgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductUnit> res = null;
        Boolean checkType = true;
        try {

            res = masProductUnitDAO.MasProductUnitgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductUnitgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductUnitgetByConAdv")
    public ResponseEntity MasProductUnitgetByConAdv(@RequestBody MasProductUnitgetByConAdvReq req) {

        log.info("============= Start API MasProductUnitgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductUnit> res = null;
        Boolean checkType = true;
        try {

            res = masProductUnitDAO.MasProductUnitgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductUnitgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductUnitgetByCon")
    public ResponseEntity MasProductUnitgetByCon(@RequestBody MasProductUnitgetByConReq req) {

        log.info("============= Start API MasProductUnitgetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasProductUnit res = null;
        Boolean checkType = true;
        try {

            res = masProductUnitDAO.MasProductUnitgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductUnitgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductUnitinsAll")
    public ResponseEntity MasProductUnitinsAll(@RequestBody MasProductUnit req) {


        log.info("============= Start API MasProductUnitinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductUnitinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductUnitDAO.MasProductUnitinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductUnitinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductUnitupdAll")
    public ResponseEntity MasProductUnitupdAll(@RequestBody MasProductUnit req) {

        log.info("============= Start API MasProductUnitupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductUnitDAO.MasProductUnitupdAll(req)) {
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
        log.info("============= End API MasProductUnitupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductUnitupdDelete")
    public ResponseEntity MasProductUnitupdDelete(@RequestBody MasProductUnitupdDeleteReq req) {

        log.info("============= Start API MasProductUnitupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductUnitDAO.MasProductUnitupdDelete(req)) {
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
        log.info("============= End API MasProductUnitupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
