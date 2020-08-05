package com.xcs.phase2.controller.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.adjust.AdjustCompareListDAO;
import com.xcs.phase2.model.adjust.AdjustCompareList;
import com.xcs.phase2.request.adjust.AdjustCompareListgetByConAdvReq;
import com.xcs.phase2.request.adjust.AdjustCompareListgetByKeywordReq;
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
public class AdjustCompareListController {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareListController.class);

    @Autowired
    private AdjustCompareListDAO compareListDAO;

    @PostMapping(value = "/AdjustCompareListgetByKeyword")
    public ResponseEntity AdjustCompareListgetByKeyword(@RequestBody AdjustCompareListgetByKeywordReq req) {

        log.info("============= Start API AdjustCompareListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<AdjustCompareList> res = null;
        Boolean checkType = true;
        try {

            res = compareListDAO.AdjustCompareListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API AdjustCompareListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareListgetByConAdv")
    public ResponseEntity AdjustCompareListgetByConAdv(@RequestBody AdjustCompareListgetByConAdvReq req) {

        log.info("============= Start API AdjustCompareListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<AdjustCompareList> res = null;
        Boolean checkType = true;
        try {

            res = compareListDAO.AdjustCompareListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API AdjustCompareListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
