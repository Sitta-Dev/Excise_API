package com.xcs.phase2.controller.target;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.target.TargetListDAO;
import com.xcs.phase2.model.target.TargetList;
import com.xcs.phase2.request.target.TargetListgetByConAdvReq;
import com.xcs.phase2.request.target.TargetListgetByKeywordReq;
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
public class TargetListController {

    private static final Logger log = LoggerFactory.getLogger(TargetListController.class);

    @Autowired
    private TargetListDAO targetListDAO;

    @PostMapping(value = "/TargetListgetByKeyword")
    public ResponseEntity TargetListgetByKeyword(@RequestBody TargetListgetByKeywordReq req) {

        log.info("============= Start API TargetListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<TargetList> res = null;
        Boolean checkType = true;
        try {

            res = targetListDAO.TargetListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API TargetListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/TargetListgetByConAdv")
    public ResponseEntity TargetListgetByConAdv(@RequestBody TargetListgetByConAdvReq req) {

        log.info("============= Start API TargetListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<TargetList> res = null;
        Boolean checkType = true;
        try {

            res = targetListDAO.TargetListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API TargetListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
