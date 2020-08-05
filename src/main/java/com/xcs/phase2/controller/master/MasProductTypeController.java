package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductTypeDAO;
import com.xcs.phase2.model.master.MasProductType;
import com.xcs.phase2.request.master.MasProductTypegetByConReq;
import com.xcs.phase2.request.master.MasProductTypeupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductTypeinsAllResponse;
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
public class MasProductTypeController {

    private static final Logger log = LoggerFactory.getLogger(MasProductTypeController.class);

    @Autowired
    private MasProductTypeDAO masProductTypeDAO;

    @PostMapping(value = "/MasProductTypegetByCon")
    public ResponseEntity MasProductTypegetByCon(@RequestBody MasProductTypegetByConReq req) {

        log.info("============= Start API MasProductTypegetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductType> res = null;
        Boolean checkType = true;
        try {

            res = masProductTypeDAO.MasProductTypegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductTypegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductTypeinsAll")
    public ResponseEntity MasProductTypeinsAll(@RequestBody MasProductType req) {


        log.info("============= Start API MasProductTypeinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductTypeinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductTypeDAO.MasProductTypeinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductTypeinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductTypeupdAll")
    public ResponseEntity MasProductTypeupdAll(@RequestBody MasProductType req) {

        log.info("============= Start API MasProductTypeupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductTypeDAO.MasProductTypeupdAll(req)) {
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
        log.info("============= End API MasProductTypeupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductTypeupdDelete")
    public ResponseEntity MasProductTypeupdDelete(@RequestBody MasProductTypeupdDeleteReq req) {

        log.info("============= Start API MasProductTypeupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductTypeDAO.MasProductTypeupdDelete(req)) {
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
        log.info("============= End API MasProductTypeupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
