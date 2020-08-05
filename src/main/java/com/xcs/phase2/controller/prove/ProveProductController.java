package com.xcs.phase2.controller.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveProductDAO;
import com.xcs.phase2.model.prove.ProveProduct;
import com.xcs.phase2.request.prove.ProveProductgetByProductIdReq;
import com.xcs.phase2.request.prove.ProveProductgetByProveIdReq;
import com.xcs.phase2.request.prove.ProveProductupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.prove.ProveProductinsAllResponse;
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
public class ProveProductController {

    private static final Logger log = LoggerFactory.getLogger(ProveProductController.class);

    @Autowired
    private ProveProductDAO proveProductDAO;

    @PostMapping(value = "/ProveProductgetByProductId")
    public ResponseEntity ProveProductgetByProductId(@RequestBody ProveProductgetByProductIdReq req) {


        log.info("============= Start API ProveProductgetByProductId ================");
        MessageResponse msg = new MessageResponse();
        ProveProduct res = null;
        Boolean checkType = true;
        try {

            res = proveProductDAO.ProveProductgetByProductId(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveProductgetByProductId =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveProductgetByProveId")
    public ResponseEntity ProveProductgetByProveId(@RequestBody ProveProductgetByProveIdReq req) {

        log.info("============= Start API ProveProductgetByProveId ================");
        MessageResponse msg = new MessageResponse();
        List<ProveProduct> res = null;
        Boolean checkType = true;
        try {

            res = proveProductDAO.ProveProductgetByProveId(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveProductgetByProveId =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveProductinsAll")
    public ResponseEntity ProveProductinsAll(@RequestBody ProveProduct req) {

        log.info("============= Start API ProveProductinsAll ================");
        ProveProductinsAllResponse res = null;
        try {

            res = proveProductDAO.ProveProductinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API ProveProductinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveProductupdByCon")
    public ResponseEntity ProveProductupdByCon(@RequestBody ProveProduct req) {

        log.info("============= Start API ProveProductupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveProductDAO.ProveProductupdByCon(req)){
                msg.setIsSuccess(Message.TRUE);
                msg.setMsg(Message.COMPLETE);
            }else{
                msg.setIsSuccess(Message.FALSE);
                msg.setMsg(Message.NOT_COMPLETE);
            }

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API ProveProductupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveProductupdDelete")
    public ResponseEntity ProveProductupdDelete(@RequestBody ProveProductupdDeleteReq req) {

        log.info("============= Start API ProveProductupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveProductDAO.ProveProductupdDelete(req)){
                msg.setIsSuccess(Message.TRUE);
                msg.setMsg(Message.COMPLETE);
            }else{
                msg.setIsSuccess(Message.FALSE);
                msg.setMsg(Message.NOT_COMPLETE);
            }

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API ProveProductupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
