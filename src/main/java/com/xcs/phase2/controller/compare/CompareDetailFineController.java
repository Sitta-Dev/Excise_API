package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.CompareDetailFineDAO;
import com.xcs.phase2.model.compare.CompareDetailFine;
import com.xcs.phase2.request.compare.CompareDetailFineupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.compare.CompareDetailFineinsAllResponse;
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
public class CompareDetailFineController {

    private static final Logger log = LoggerFactory.getLogger(CompareDetailFineController.class);

    @Autowired
    private CompareDetailFineDAO compareDetailFineDAO;

    @PostMapping(value = "/CompareDetailFineinsAll")
    public ResponseEntity CompareDetailFineinsAll(@RequestBody List<CompareDetailFine> req) {

        log.info("============= Start API CompareDetailFineinsAll ================");
        CompareDetailFineinsAllResponse res = null;
        try {

            res = compareDetailFineDAO.CompareDetailFineinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API CompareDetailFineinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareDetailFineupdByCon")
    public ResponseEntity CompareDetailFineupdByCon(@RequestBody List<CompareDetailFine> req) {

        log.info("============= Start API CompareDetailFineupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailFineDAO.CompareDetailFineupdByCon(req)){
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
        log.info("============= End API CompareDetailFineupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareDetailFineupdDelete")
    public ResponseEntity CompareDetailFineupdDelete(@RequestBody List<CompareDetailFineupdDeleteReq> req) {

        log.info("============= Start API CompareDetailFineupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailFineDAO.CompareDetailFineupdDelete(req)){
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
        log.info("============= End API CompareDetailFineupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
