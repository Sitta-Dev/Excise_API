package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductCategoryDAO;
import com.xcs.phase2.model.master.MasProductCategory;
import com.xcs.phase2.request.master.MasProductCategorygetByConReq;
import com.xcs.phase2.request.master.MasProductCategoryupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductCategoryinsAllResponse;
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
public class MasProductCategoryController {

    private static final Logger log = LoggerFactory.getLogger(MasProductCategoryController.class);

    @Autowired
    private MasProductCategoryDAO masProductCategoryDAO;

    @PostMapping(value = "/MasProductCategorygetByCon")
    public ResponseEntity MasProductCategorygetByCon(@RequestBody MasProductCategorygetByConReq req) {

        log.info("============= Start API MasProductCategorygetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductCategory> res = null;
        Boolean checkType = true;
        try {

            res = masProductCategoryDAO.MasProductCategorygetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductCategorygetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductCategoryinsAll")
    public ResponseEntity MasProductCategoryinsAll(@RequestBody MasProductCategory req) {


        log.info("============= Start API MasProductCategoryinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductCategoryinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductCategoryDAO.MasProductCategoryinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductCategoryinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductCategoryupdAll")
    public ResponseEntity MasProductCategoryupdAll(@RequestBody MasProductCategory req) {

        log.info("============= Start API MasProductCategoryupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductCategoryDAO.MasProductCategoryupdAll(req)) {
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
        log.info("============= End API MasProductCategoryupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductCategoryupdDelete")
    public ResponseEntity MasProductCategoryupdDelete(@RequestBody MasProductCategoryupdDeleteReq req) {

        log.info("============= Start API MasProductCategoryupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductCategoryDAO.MasProductCategoryupdDelete(req)) {
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
        log.info("============= End API MasProductCategoryupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
