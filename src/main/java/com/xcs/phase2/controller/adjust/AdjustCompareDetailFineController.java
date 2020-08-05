package com.xcs.phase2.controller.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.adjust.AdjustCompareDetailFineDAO;
import com.xcs.phase2.model.adjust.AdjustCompareDetailFine;
import com.xcs.phase2.request.adjust.AdjustCompareDetailFineupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.adjust.AdjustCompareDetailFineinsAllResponse;
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
public class AdjustCompareDetailFineController {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareDetailFineController.class);

    @Autowired
    private AdjustCompareDetailFineDAO compareDetailFineDAO;

    @PostMapping(value = "/AdjustCompareDetailFineinsAll")
    public ResponseEntity AdjustCompareDetailFineinsAll(@RequestBody List<AdjustCompareDetailFine> req) {

        log.info("============= Start API AdjustCompareDetailFineinsAll ================");
        AdjustCompareDetailFineinsAllResponse res = null;
        try {

            res = compareDetailFineDAO.AdjustCompareDetailFineinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API AdjustCompareDetailFineinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareDetailFineupdByCon")
    public ResponseEntity AdjustCompareDetailFineupdByCon(@RequestBody List<AdjustCompareDetailFine> req) {

        log.info("============= Start API AdjustCompareDetailFineupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailFineDAO.AdjustCompareDetailFineupdByCon(req)){
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
        log.info("============= End API AdjustCompareDetailFineupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareDetailFineupdDelete")
    public ResponseEntity AdjustCompareDetailFineupdDelete(@RequestBody List<AdjustCompareDetailFineupdDeleteReq> req) {

        log.info("============= Start API AdjustCompareDetailFineupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailFineDAO.AdjustCompareDetailFineupdDelete(req)){
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
        log.info("============= End API AdjustCompareDetailFineupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
