package com.xcs.phase2.controller.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.adjust.AdjustCompareArrestDAO;
import com.xcs.phase2.model.adjust.AdjustCompareArrest;
import com.xcs.phase2.model.adjust.AdjustCompareArrestNew;
import com.xcs.phase2.request.adjust.AdjustCompareArrestgetByIndictmentIDReq;
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
public class AdjustCompareArrestController {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareArrestController.class);

    @Autowired
    private AdjustCompareArrestDAO compareArrestIndictmentDAO;

    @PostMapping(value = "/AdjustCompareArrestgetByIndictmentIDBackUp")
    public ResponseEntity AdjustCompareArrestgetByIndictmentIDBackUp(@RequestBody AdjustCompareArrestgetByIndictmentIDReq req) {


        log.info("============= Start API AdjustCompareArrestgetByIndictmentID ================");
        MessageResponse msg = new MessageResponse();
        List<AdjustCompareArrest> res = null;
        Boolean checkType = true;
        try {

            res = compareArrestIndictmentDAO.AdjustCompareArrestgetByIndictmentID(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API AdjustCompareArrestgetByIndictmentID =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareArrestgetByIndictmentID")
    public ResponseEntity AdjustCompareArrestgetByIndictmentID(@RequestBody AdjustCompareArrestgetByIndictmentIDReq req) {


        log.info("============= Start API AdjustCompareArrestgetByIndictmentID ================");
        MessageResponse msg = new MessageResponse();
        List<AdjustCompareArrestNew> res = null;
        Boolean checkType = true;
        try {

            res = compareArrestIndictmentDAO.AdjustCompareArrestgetByIndictmentIDNew(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API AdjustCompareArrestgetByIndictmentID =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
