package com.xcs.phase2.controller.target;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.target.TargetItemDAO;
import com.xcs.phase2.model.target.TargetItem;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.target.TargetIteminsAllResponse;
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
public class TargetItemController {

    private static final Logger log = LoggerFactory.getLogger(TargetItemController.class);

    @Autowired
    private TargetItemDAO targetItemDAO;

    @PostMapping(value = "/TargetIteminsAll")
    public ResponseEntity TargetIteminsAll(@RequestBody List<TargetItem> req) {

        log.info("============= Start API TargetIteminsAll ================");
        TargetIteminsAllResponse res = null;
        try {

            res = targetItemDAO.TargetIteminsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API TargetIteminsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/TargetItemupdByCon")
    public ResponseEntity TargetItemupdByCon(@RequestBody List<TargetItem> req) {

        log.info("============= Start API TargetItemupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(targetItemDAO.TargetItemupdByCon(req)){
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
        log.info("============= End API TargetItemupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
