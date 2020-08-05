package com.xcs.phase2.controller.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveScienceDAO;
import com.xcs.phase2.model.prove.ProveScience;
import com.xcs.phase2.request.prove.ProveSciencegetByConReq;
import com.xcs.phase2.request.prove.ProveScienceupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.prove.ProveScienceinsAllResponse;
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
public class ProveScienceController {

    private static final Logger log = LoggerFactory.getLogger(ProveScienceController.class);

    @Autowired
    private ProveScienceDAO proveScienceDAO;

    @PostMapping(value = "/ProveSciencegetByCon")
    public ResponseEntity ProveSciencegetByCon(@RequestBody ProveSciencegetByConReq req) {

        log.info("============= Start API ProveSciencegetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<ProveScience> res = null;
        Boolean checkType = true;
        try {

            res = proveScienceDAO.ProveSciencegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveSciencegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveScienceinsAll")
    public ResponseEntity ProveScienceinsAll(@RequestBody ProveScience req) {

        log.info("============= Start API ProveScienceinsAll ================");
        ProveScienceinsAllResponse res = null;
        try {

            res = proveScienceDAO.ProveScienceinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API ProveScienceinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveScienceupdByCon")
    public ResponseEntity ProveScienceupdByCon(@RequestBody ProveScience req) {

        log.info("============= Start API ProveScienceupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveScienceDAO.ProveScienceupdByCon(req)){
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
        log.info("============= End API ProveScienceupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveScienceupdDelete")
    public ResponseEntity ProveScienceupdDelete(@RequestBody ProveScienceupdDeleteReq req) {

        log.info("============= Start API ProveScienceupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveScienceDAO.ProveScienceupdDelete(req)){
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
        log.info("============= End API ProveScienceupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
