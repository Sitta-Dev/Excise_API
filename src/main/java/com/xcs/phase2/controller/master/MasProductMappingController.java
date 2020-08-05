package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductMappingDAO;
import com.xcs.phase2.model.master.MasProductMapping;
import com.xcs.phase2.model.master.ProductMapping;
import com.xcs.phase2.request.master.MasProductMappinggetByConAdvReq;
import com.xcs.phase2.request.master.MasProductMappinggetByConReq;
import com.xcs.phase2.request.master.MasProductMappinggetByKeywordReq;
import com.xcs.phase2.request.master.MasProductMappingupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasProductMappinginsAllResponse;
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
public class MasProductMappingController {

    private static final Logger log = LoggerFactory.getLogger(MasProductMappingController.class);

    @Autowired
    private MasProductMappingDAO masProductMappingDAO;

    @PostMapping(value = "/MasProductMappinggetByKeyword")
    public ResponseEntity MasProductMappinggetByKeyword(@RequestBody MasProductMappinggetByKeywordReq req) {

        log.info("============= Start API MasProductMappinggetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<ProductMapping> res = null;
        Boolean checkType = true;
        try {

            res = masProductMappingDAO.MasProductMappinggetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductMappinggetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductMappinggetByConAdv")
    public ResponseEntity MasProductMappinggetByConAdv(@RequestBody MasProductMappinggetByConAdvReq req) {

        log.info("============= Start API MasProductMappinggetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<ProductMapping> res = null;
        Boolean checkType = true;
        try {

            res = masProductMappingDAO.MasProductMappinggetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductMappinggetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductMappinggetByCon")
    public ResponseEntity MasProductMappinggetByCon(@RequestBody MasProductMappinggetByConReq req) {

        log.info("============= Start API MasProductMappinggetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasProductMapping res = null;
        Boolean checkType = true;
        try {

            res = masProductMappingDAO.MasProductMappinggetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductMappinggetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductMappinginsAll")
    public ResponseEntity MasProductMappinginsAll(@RequestBody MasProductMapping req) {


        log.info("============= Start API MasProductMappinginsAll ================");
        MessageResponse msg = new MessageResponse();
        MasProductMappinginsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = masProductMappingDAO.MasProductMappinginsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductMappinginsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductMappingupdAll")
    public ResponseEntity MasProductMappingupdAll(@RequestBody MasProductMapping req) {

        log.info("============= Start API MasProductMappingupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductMappingDAO.MasProductMappingupdAll(req)) {
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
        log.info("============= End API MasProductMappingupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasProductMappingupdDelete")
    public ResponseEntity MasProductMappingupdDelete(@RequestBody MasProductMappingupdDeleteReq req) {

        log.info("============= Start API MasProductMappingupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (masProductMappingDAO.MasProductMappingupdDelete(req)) {
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
        log.info("============= End API MasProductMappingupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
