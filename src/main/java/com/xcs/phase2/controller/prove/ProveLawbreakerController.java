package com.xcs.phase2.controller.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveLawbreakerDAO;
import com.xcs.phase2.model.prove.ProveLawbreaker;
import com.xcs.phase2.request.prove.ProveLawbreakergetByConReq;
import com.xcs.phase2.request.prove.ProveLawbreakerupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.prove.ProveLawbreakerinsAllResponse;
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
public class ProveLawbreakerController {

    private static final Logger log = LoggerFactory.getLogger(ProveLawbreakerController.class);

    @Autowired
    private ProveLawbreakerDAO proveLawbreakerDAO;

    @PostMapping(value = "/ProveLawbreakergetByCon")
    public ResponseEntity ProveLawbreakergetByCon(@RequestBody ProveLawbreakergetByConReq req) {

        log.info("============= Start API ProveLawbreakergetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<ProveLawbreaker> res = null;
        Boolean checkType = true;
        try {

            res = proveLawbreakerDAO.ProveLawbreakergetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveLawbreakergetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveLawbreakerinsAll")
    public ResponseEntity ProveLawbreakerinsAll(@RequestBody ProveLawbreaker req) {

        log.info("============= Start API ProveLawbreakerinsAll ================");
        ProveLawbreakerinsAllResponse res = null;
        try {

            res = proveLawbreakerDAO.ProveLawbreakerinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API ProveLawbreakerinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveLawbreakerupdByCon")
    public ResponseEntity ProveLawbreakerupdByCon(@RequestBody ProveLawbreaker req) {

        log.info("============= Start API ProveLawbreakerupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveLawbreakerDAO.ProveLawbreakerupdByCon(req)){
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
        log.info("============= End API ProveLawbreakerupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveLawbreakerupdDelete")
    public ResponseEntity ProveLawbreakerupdDelete(@RequestBody ProveLawbreakerupdDeleteReq req) {

        log.info("============= Start API ProveLawbreakerupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveLawbreakerDAO.ProveLawbreakerupdDelete(req)){
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
        log.info("============= End API ProveLawbreakerupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

}
