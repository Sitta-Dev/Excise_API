package com.xcs.phase2.controller.deliverystorage;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.deliverystorage.DeliveryStorageDAO;
import com.xcs.phase2.model.deliverystorage.DeliveryStorage;
import com.xcs.phase2.model.deliverystorage.DeliveryStorageEvidenceIn;
import com.xcs.phase2.model.deliverystorage.DeliveryStorageProduct;
import com.xcs.phase2.request.deliverystorage.DeliverStoragegetByConReq;
import com.xcs.phase2.request.deliverystorage.DeliverStoragegetByCreateReq;
import com.xcs.phase2.request.deliverystorage.DeliverStoragegetProductReq;
import com.xcs.phase2.request.deliverystorage.DeliverStorageupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.deliverystorage.DeliveryStorageinsAllResponse;
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
public class DeliveryStorageController {

    private static final Logger log = LoggerFactory.getLogger(DeliveryStorageController.class);

    @Autowired
    private DeliveryStorageDAO deliveryStorageDAO;

    @PostMapping(value = "/DeliverStoragegetByCreate")
    public ResponseEntity DeliverStoragegetByCreate(@RequestBody DeliverStoragegetByCreateReq req) {


        log.info("============= Start API DeliverStoragegetByCreate ================");
        MessageResponse msg = new MessageResponse();
        DeliveryStorage res = null;
        Boolean checkType = true;
        try {

            res = deliveryStorageDAO.DeliverStoragegetByCreate(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API DeliverStoragegetByCreate =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/DeliverStorageetProduct")
    public ResponseEntity DeliverStorageetProduct(@RequestBody DeliverStoragegetProductReq req) {

        log.info("============= Start API DeliverStorageetProduct ================");
        MessageResponse msg = new MessageResponse();
        List<DeliveryStorageProduct> res = null;
        Boolean checkType = true;
        try {

            res = deliveryStorageDAO.DeliverStorageetProduct(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API DeliverStorageetProduct =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/DeliveryStorageinsAll")
    public ResponseEntity DeliveryStorageinsAll(@RequestBody DeliveryStorageEvidenceIn req) {

        log.info("============= Start API DeliveryStorageinsAll ================");
        DeliveryStorageinsAllResponse res = null;
        try {

            res = deliveryStorageDAO.DeliveryStorageinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API DeliveryStorageinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/DeliverStoragegetByCon")
    public ResponseEntity DeliverStoragegetByCon(@RequestBody DeliverStoragegetByConReq req) {


        log.info("============= Start API DeliverStoragegetByCon ================");
        MessageResponse msg = new MessageResponse();
        DeliveryStorageEvidenceIn res = null;
        Boolean checkType = true;
        try {

            res = deliveryStorageDAO.DeliverStoragegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API DeliverStoragegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/DeliverStorageupdDelete")
    public ResponseEntity DeliverStorageupdDelete(@RequestBody DeliverStorageupdDeleteReq req) {

        log.info("============= Start API DeliverStorageupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(deliveryStorageDAO.DeliverStorageupdDelete(req)){
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
        log.info("============= End API DeliverStorageupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/DeliverStorageupdByCon")
    public ResponseEntity DeliverStorageupdByCon(@RequestBody DeliveryStorageEvidenceIn req) {

        log.info("============= Start API DeliverStorageupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(deliveryStorageDAO.DeliverStorageupdByCon(req)){
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
        log.info("============= End API DeliverStorageupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

}
