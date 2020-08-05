package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductSubBrandDAO;
import com.xcs.phase2.model.master.MasProductSubBrand;
import com.xcs.phase2.request.master.MasProductSubBrandgetByConReq;
import com.xcs.phase2.request.master.MasProductSubBrandupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductSubBrandinsAllResponse;
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
public class MasProductSubBrandController {

    private static final Logger log = LoggerFactory.getLogger(MasProductSubBrandController.class);

    @Autowired
    private MasProductSubBrandDAO masProductSubBrandDAO;

    @PostMapping(value = "/MasProductSubBrandgetByCon")
    public ResponseEntity MasProductSubBrandgetByCon(@RequestBody MasProductSubBrandgetByConReq req) {

        log.info("============= Start API MasProductSubBrandgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductSubBrand> res = null;
        Boolean checkType = true;
        try {

            res = masProductSubBrandDAO.MasProductSubBrandgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSubBrandgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSubBrandinsAll")
    public ResponseEntity MasProductSubBrandinsAll(@RequestBody MasProductSubBrand req) {


        log.info("============= Start API MasProductSubBrandinsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductSubBrandinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductSubBrandDAO.MasProductSubBrandinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductSubBrandinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSubBrandupdAll")
    public ResponseEntity MasProductSubBrandupdAll(@RequestBody MasProductSubBrand req) {

        log.info("============= Start API MasProductSubBrandupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductSubBrandDAO.MasProductSubBrandupdAll(req)) {
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
        log.info("============= End API MasProductSubBrandupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductSubBrandupdDelete")
    public ResponseEntity MasProductSubBrandupdDelete(@RequestBody MasProductSubBrandupdDeleteReq req) {

        log.info("============= Start API MasProductSubBrandupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductSubBrandDAO.MasProductSubBrandupdDelete(req)) {
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
        log.info("============= End API MasProductSubBrandupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
