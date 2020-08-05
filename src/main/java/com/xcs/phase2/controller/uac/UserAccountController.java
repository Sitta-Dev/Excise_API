package com.xcs.phase2.controller.uac;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.UserAccountDAO;
import com.xcs.phase2.model.uac.UserAccount;
import com.xcs.phase2.request.uac.UserAccountgetByConReq;
import com.xcs.phase2.request.uac.UserAccountupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.uac.UserAccountinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {

    private static final Logger log = LoggerFactory.getLogger(UserAccountController.class);

    @Autowired
    private UserAccountDAO userAccountDAO;

    @PostMapping(value = "/UserAccountgetByCon")
    public ResponseEntity UserAccountgetByCon(@RequestBody UserAccountgetByConReq req) {


        log.info("============= Start API UserAccountgetByCon ================");
        MessageResponse msg = new MessageResponse();
        UserAccount res = null;
        Boolean checkType = true;
        try {

            res = userAccountDAO.UserAccountgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API UserAccountgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/UserAccountinsAll")
    public ResponseEntity UserAccountinsAll(@RequestBody UserAccount req) {

        log.info("============= Start API UserAccountinsAll ================");
        UserAccountinsAllResponse res = null;
        try {

            res = userAccountDAO.UserAccountinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API UserAccountinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/UserAccountupdByCon")
    public ResponseEntity UserAccountupdByCon(@RequestBody UserAccount req) {

        log.info("============= Start API UserAccountupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(userAccountDAO.UserAccountupdByCon(req)){
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
        log.info("============= End API UserAccountupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/UserAccountupdDelete")
    public ResponseEntity UserAccountupdDelete(@RequestBody UserAccountupdDeleteReq req) {

        log.info("============= Start API UserAccountupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(userAccountDAO.UserAccountupdDelete(req)){
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
        log.info("============= End API UserAccountupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
