package com.xcs.phase2.controller.deliverystorage;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.deliverystorage.DeliveryStorageListDAO;
import com.xcs.phase2.model.deliverystorage.DeliveryStorageList;
import com.xcs.phase2.request.deliverystorage.DeliverstorageListgetByConAdvReq;
import com.xcs.phase2.request.deliverystorage.DeliverstorageListgetByKeywordReq;
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
public class DeliveryStorageListController {

    private static final Logger log = LoggerFactory.getLogger(DeliveryStorageListController.class);

    @Autowired
    private DeliveryStorageListDAO deliveryStorageListDAO;

    @PostMapping(value = "/DeliverstorageListgetByKeyword")
    public ResponseEntity DeliverstorageListgetByKeyword(@RequestBody DeliverstorageListgetByKeywordReq req) {

        log.info("============= Start API DeliverstorageListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<DeliveryStorageList> res = null;
        Boolean checkType = true;
        try {

            res = deliveryStorageListDAO.DeliverstorageListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API DeliverstorageListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/DeliverstorageListgetByConAdv")
    public ResponseEntity DeliverstorageListgetByConAdv(@RequestBody DeliverstorageListgetByConAdvReq req) {

        log.info("============= Start API DeliverstorageListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<DeliveryStorageList> res = null;
        Boolean checkType = true;
        try {

            res = deliveryStorageListDAO.DeliverstorageListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API DeliverstorageListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

}
