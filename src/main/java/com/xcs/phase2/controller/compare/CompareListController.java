package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.CompareListDAO;
import com.xcs.phase2.model.compare.CompareList;
import com.xcs.phase2.request.compare.CompareListgetByConAdvReq;
import com.xcs.phase2.request.compare.CompareListgetByKeywordReq;
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
public class CompareListController {

    private static final Logger log = LoggerFactory.getLogger(CompareListController.class);

    @Autowired
    private CompareListDAO compareListDAO;

    @PostMapping(value = "/CompareListgetByKeyword")
    public ResponseEntity CompareListgetByKeyword(@RequestBody CompareListgetByKeywordReq req) {

        log.info("============= Start API CompareListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<CompareList> res = null;
        Boolean checkType = true;
        try {

            res = compareListDAO.CompareListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CompareListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareListgetByConAdv")
    public ResponseEntity CompareListgetByConAdv(@RequestBody CompareListgetByConAdvReq req) {

        log.info("============= Start API CompareListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<CompareList> res = null;
        Boolean checkType = true;
        try {

            res = compareListDAO.CompareListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CompareListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
