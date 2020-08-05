package com.xcs.phase2.controller.uac;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.UserAccountPermissionDAO;
import com.xcs.phase2.model.uac.UserAccountPermission;
import com.xcs.phase2.request.uac.UserAccountPermissionCheckPermissionReq;
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
public class UserAccountPermissionController {

    private static final Logger log = LoggerFactory.getLogger(UserAccountPermissionController.class);

    @Autowired
    private UserAccountPermissionDAO userAccountPermissionDAO;

    @PostMapping(value = "/UserAccountPermissionCheckPermission")
    public ResponseEntity UserAccountPermissionCheckPermission(@RequestBody UserAccountPermissionCheckPermissionReq req) {

        log.info("============= Start API UserAccountPermissionCheckPermission ================");
        MessageResponse msg = new MessageResponse();
        List<UserAccountPermission> res = null;
        Boolean checkType = true;
        try {

            res = userAccountPermissionDAO.UserAccountPermissionCheckPermission(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API UserAccountPermissionCheckPermission =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }


}
