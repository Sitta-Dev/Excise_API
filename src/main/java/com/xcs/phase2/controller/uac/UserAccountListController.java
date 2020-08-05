package com.xcs.phase2.controller.uac;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.UserAccountListDAO;
import com.xcs.phase2.model.uac.UserAccountList;
import com.xcs.phase2.request.uac.UserAccountListgetByConAdvReq;
import com.xcs.phase2.request.uac.UserAccountListgetByKeywordReq;
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
public class UserAccountListController {

    private static final Logger log = LoggerFactory.getLogger(UserAccountListController.class);

    @Autowired
    private UserAccountListDAO userAccountListDAO;

    @PostMapping(value = "/UserAccountListgetByKeyword")
    public ResponseEntity UserAccountListgetByKeyword(@RequestBody UserAccountListgetByKeywordReq req) {

        log.info("============= Start API UserAccountListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<UserAccountList> res = null;
        Boolean checkType = true;
        try {

            res = userAccountListDAO.UserAccountListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API UserAccountListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/UserAccountListgetByConAdv")
    public ResponseEntity UserAccountListgetByConAdv(@RequestBody UserAccountListgetByConAdvReq req) {

        log.info("============= Start API UserAccountListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<UserAccountList> res = null;
        Boolean checkType = true;
        try {

            res = userAccountListDAO.UserAccountListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API UserAccountListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
