package com.xcs.phase2.controller.uac;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.RoleListDAO;
import com.xcs.phase2.model.uac.RoleList;
import com.xcs.phase2.request.uac.RoleListgetByConAdvReq;
import com.xcs.phase2.request.uac.RoleListgetByKeywordReq;
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
public class RoleListController {

    private static final Logger log = LoggerFactory.getLogger(RoleListController.class);

    @Autowired
    private RoleListDAO roleListDAO;

    @PostMapping(value = "/RoleListgetByKeyword")
    public ResponseEntity RoleListgetByKeyword(@RequestBody RoleListgetByKeywordReq req) {

        log.info("============= Start API RoleListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<RoleList> res = null;
        Boolean checkType = true;
        try {

            res = roleListDAO.RoleListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RoleListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RoleListgetByConAdv")
    public ResponseEntity RoleListgetByConAdv(@RequestBody RoleListgetByConAdvReq req) {

        log.info("============= Start API RoleListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<RoleList> res = null;
        Boolean checkType = true;
        try {

            res = roleListDAO.RoleListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RoleListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
