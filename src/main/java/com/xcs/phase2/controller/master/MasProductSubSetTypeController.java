package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductSubSetTypeDAO;
import com.xcs.phase2.model.master.MasProductSubSetType;
import com.xcs.phase2.request.master.MasProductSubSetTypegetByConReq;
import com.xcs.phase2.request.master.MasProductSubSetTypeupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductSubSetTypeinsAllResponse;
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
public class MasProductSubSetTypeController {

    private static final Logger log = LoggerFactory.getLogger(MasProductSubSetTypeController.class);

    @Autowired
    private MasProductSubSetTypeDAO masProductSubSetTypeDAO;

    @PostMapping(value = "/MasProductSubSetTypegetByCon")
    public ResponseEntity MasProductSubSetTypegetByCon(@RequestBody MasProductSubSetTypegetByConReq req) {

        log.info("============= Start API MasProductSubSetTypegetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductSubSetType> res = null;
        Boolean checkType = true;
        try {

            res = masProductSubSetTypeDAO.MasProductSubSetTypegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSubSetTypegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSubSetTypeinsAll")
    public ResponseEntity MasProductSubSetTypeinsAll(@RequestBody MasProductSubSetType req) {


        log.info("============= Start API MasProductSubSetTypeinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductSubSetTypeinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductSubSetTypeDAO.MasProductSubSetTypeinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSubSetTypeinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSubSetTypeupdAll")
    public ResponseEntity MasProductSubSetTypeupdAll(@RequestBody MasProductSubSetType req) {

        log.info("============= Start API MasProductSubSetTypeupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductSubSetTypeDAO.MasProductSubSetTypeupdAll(req)) {
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
        log.info("============= End API MasProductSubSetTypeupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSubSetTypeupdDelete")
    public ResponseEntity MasProductSubSetTypeupdDelete(@RequestBody MasProductSubSetTypeupdDeleteReq req) {

        log.info("============= Start API MasProductSubSetTypeupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductSubSetTypeDAO.MasProductSubSetTypeupdDelete(req)) {
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
        log.info("============= End API MasProductSubSetTypeupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
