package com.xcs.phase2.controller.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.adjust.AdjustCompareDetailStaffDAO;
import com.xcs.phase2.model.adjust.AdjustCompareStaff;
import com.xcs.phase2.request.adjust.AdjustCompareDetailStaffupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.adjust.AdjustCompareDetailStaffinsAllResponse;
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
public class AdjustCompareDetailStaffController {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareDetailStaffController.class);

    @Autowired
    private AdjustCompareDetailStaffDAO compareDetailStaffDAO;

    @PostMapping(value = "/AdjustCompareDetailStaffinsAll")
    public ResponseEntity AdjustCompareDetailStaffinsAll(@RequestBody List<AdjustCompareStaff> req) {

        log.info("============= Start API AdjustCompareDetailStaffinsAll ================");
        AdjustCompareDetailStaffinsAllResponse res = null;
        try {

            res = compareDetailStaffDAO.AdjustCompareDetailStaffinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API AdjustCompareDetailStaffinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareDetailStaffupdByCon")
    public ResponseEntity AdjustCompareDetailStaffupdByCon(@RequestBody List<AdjustCompareStaff> req) {

        log.info("============= Start API AdjustCompareDetailStaffupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailStaffDAO.AdjustCompareDetailStaffupdByCon(req)){
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
        log.info("============= End API AdjustCompareDetailStaffupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareDetailStaffupdDelete")
    public ResponseEntity AdjustCompareDetailStaffupdDelete(@RequestBody List<AdjustCompareDetailStaffupdDeleteReq> req) {

        log.info("============= Start API AdjustCompareDetailStaffupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailStaffDAO.AdjustCompareDetailStaffupdDelete(req)){
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
        log.info("============= End API AdjustCompareDetailStaffupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
