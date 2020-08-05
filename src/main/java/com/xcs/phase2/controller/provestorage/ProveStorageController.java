package com.xcs.phase2.controller.provestorage;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.provestorage.ProveStorageDAO;
import com.xcs.phase2.model.provestorage.ProveStorage;
import com.xcs.phase2.model.provestorage.ProveStorageEvidenceIn;
import com.xcs.phase2.model.provestorage.ProveStorageProduct;
import com.xcs.phase2.request.provestorage.ProveStoragegetByConReq;
import com.xcs.phase2.request.provestorage.ProveStoragegetByCreateReq;
import com.xcs.phase2.request.provestorage.ProveStoragegetProductReq;
import com.xcs.phase2.request.provestorage.ProveStorageupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
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
public class ProveStorageController {

    private static final Logger log = LoggerFactory.getLogger(ProveStorageController.class);

    @Autowired
    private ProveStorageDAO proveStorageDAO;

    @PostMapping(value = "/ProveStoragegetByCreate")
    public ResponseEntity ProveStoragegetByCreate(@RequestBody ProveStoragegetByCreateReq req) {


        log.info("============= Start API ProveStoragegetByCreate ================");
        MessageResponse msg = new MessageResponse();
        ProveStorage res = null;
        Boolean checkType = true;
        try {

            res = proveStorageDAO.ProveStoragegetByCreate(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveStoragegetByCreate =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveStorageetProduct")
    public ResponseEntity ProveStorageetProduct(@RequestBody ProveStoragegetProductReq req) {

        log.info("============= Start API ProveStorageetProduct ================");
        MessageResponse msg = new MessageResponse();
        List<ProveStorageProduct> res = null;
        Boolean checkType = true;
        try {

            res = proveStorageDAO.ProveStorageProduct(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveStorageetProduct =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveStorageinsAll")
    public ResponseEntity ProveStorageinsAll(@RequestBody ProveStorageEvidenceIn req) {

        log.info("============= Start API ProveStorageinsAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveStorageDAO.ProveStorageinsAll(req)){
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
        log.info("============= End API ProveStorageinsAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveStoragegetByCon")
    public ResponseEntity ProveStoragegetByCon(@RequestBody ProveStoragegetByConReq req) {


        log.info("============= Start API ProveStoragegetByCon ================");
        MessageResponse msg = new MessageResponse();
        ProveStorageEvidenceIn res = null;
        Boolean checkType = true;
        try {

            res = proveStorageDAO.ProveStoragegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveStoragegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveStorageupdDelete")
    public ResponseEntity ProveStorageupdDelete(@RequestBody ProveStorageupdDeleteReq req) {

        log.info("============= Start API ProveStorageupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveStorageDAO.ProveStorageupdDelete(req)){
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
        log.info("============= End API ProveStorageupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveStorageupdByCon")
    public ResponseEntity ProveStorageupdByCon(@RequestBody ProveStorageEvidenceIn req) {

        log.info("============= Start API ProveStorageupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveStorageDAO.ProveStorageupdByCon(req)){
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
        log.info("============= End API ProveStorageupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

}
