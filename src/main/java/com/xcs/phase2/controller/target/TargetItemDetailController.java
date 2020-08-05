package com.xcs.phase2.controller.target;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.target.TargetItemDetailDAO;
import com.xcs.phase2.model.target.TargetItemDetail;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.target.TargetItemDeatailinsAllResponse;
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
public class TargetItemDetailController {

    private static final Logger log = LoggerFactory.getLogger(TargetItemController.class);

    @Autowired
    private TargetItemDetailDAO targetItemDetailDAO;

    @PostMapping(value = "/TargetItemDeatailinsAll")
    public ResponseEntity TargetItemDeatailinsAll(@RequestBody List<TargetItemDetail> req) {

        log.info("============= Start API TargetItemDeatailinsAll ================");
        TargetItemDeatailinsAllResponse res = null;
        try {

            res = targetItemDetailDAO.TargetItemDeatailinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API TargetItemDeatailinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/TargetItemDetailupdByCon")
    public ResponseEntity TargetItemDetailupdByCon(@RequestBody List<TargetItemDetail> req) {

        log.info("============= Start API TargetItemDetailupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(targetItemDetailDAO.TargetItemDetailupdByCon(req)){
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
        log.info("============= End API TargetItemDetailupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
