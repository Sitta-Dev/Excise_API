package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductBrandDAO;
import com.xcs.phase2.model.master.MasProductBrand;
import com.xcs.phase2.request.master.MasProductBrandgetByConReq;
import com.xcs.phase2.request.master.MasProductBrandupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductBrandinsAllResponse;
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
public class MasProductBrandController {

    private static final Logger log = LoggerFactory.getLogger(MasProductBrandController.class);

    @Autowired
    private MasProductBrandDAO masProductBrandDAO;

    @PostMapping(value = "/MasProductBrandgetByCon")
    public ResponseEntity MasProductBrandgetByCon(@RequestBody MasProductBrandgetByConReq req) {

        log.info("============= Start API MasProductBrandgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductBrand> res = null;
        Boolean checkType = true;
        try {

            res = masProductBrandDAO.MasProductBrandgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductBrandgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductBrandinsAll")
    public ResponseEntity MasProductBrandinsAll(@RequestBody MasProductBrand req) {


        log.info("============= Start API MasProductBrandinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductBrandinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductBrandDAO.MasProductBrandinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductBrandinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductBrandupdAll")
    public ResponseEntity MasProductBrandupdAll(@RequestBody MasProductBrand req) {

        log.info("============= Start API MasProductBrandupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductBrandDAO.MasProductBrandupdAll(req)) {
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
        log.info("============= End API MasProductBrandupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductBrandupdDelete")
    public ResponseEntity MasProductBrandupdDelete(@RequestBody MasProductBrandupdDeleteReq req) {

        log.info("============= Start API MasProductBrandupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductBrandDAO.MasProductBrandupdDelete(req)) {
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
        log.info("============= End API MasProductBrandupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
