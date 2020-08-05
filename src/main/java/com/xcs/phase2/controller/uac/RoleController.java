package com.xcs.phase2.controller.uac;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.RoleDAO;
import com.xcs.phase2.model.uac.Role;
import com.xcs.phase2.request.uac.RolegetByConReq;
import com.xcs.phase2.request.uac.RoleupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.uac.RoleinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleDAO roleDAO;

    @PostMapping(value = "/RolegetByCon")
    public ResponseEntity RolegetByCon(@RequestBody RolegetByConReq req) {


        log.info("============= Start API RolegetByCon ================");
        MessageResponse msg = new MessageResponse();
        Role res = null;
        Boolean checkType = true;
        try {

            res = roleDAO.RolegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RolegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RoleinsAll")
    public ResponseEntity RoleinsAll(@RequestBody Role req) {

        log.info("============= Start API RoleinsAll ================");
        RoleinsAllResponse res = null;
        try {

            res = roleDAO.RoleinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API RoleinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/RoleupdByCon")
    public ResponseEntity RoleupdByCon(@RequestBody Role req) {

        log.info("============= Start API RoleupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(roleDAO.RoleupdByCon(req)){
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
        log.info("============= End API RoleupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RoleupdDelete")
    public ResponseEntity RoleupdDelete(@RequestBody RoleupdDeleteReq req) {

        log.info("============= Start API RoleupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(roleDAO.RoleupdDelete(req)){
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
        log.info("============= End API RoleupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
