package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.CompareDetailStaffDAO;
import com.xcs.phase2.model.compare.CompareStaff;
import com.xcs.phase2.request.compare.CompareDetailStaffupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.compare.CompareDetailStaffinsAllResponse;
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
public class CompareDetailStaffController {

    private static final Logger log = LoggerFactory.getLogger(CompareDetailStaffController.class);

    @Autowired
    private CompareDetailStaffDAO compareDetailStaffDAO;

    @PostMapping(value = "/CompareDetailStaffinsAll")
    public ResponseEntity CompareDetailStaffinsAll(@RequestBody List<CompareStaff> req) {

        log.info("============= Start API CompareDetailStaffinsAll ================");
        CompareDetailStaffinsAllResponse res = null;
        try {

            res = compareDetailStaffDAO.CompareDetailStaffinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API CompareDetailStaffinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareDetailStaffupdByCon")
    public ResponseEntity CompareDetailStaffupdByCon(@RequestBody List<CompareStaff> req) {

        log.info("============= Start API CompareDetailStaffupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailStaffDAO.CompareDetailStaffupdByCon(req)){
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
        log.info("============= End API CompareDetailStaffupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareDetailStaffupdDelete")
    public ResponseEntity CompareDetailStaffupdDelete(@RequestBody List<CompareDetailStaffupdDeleteReq> req) {

        log.info("============= Start API CompareDetailStaffupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailStaffDAO.CompareDetailStaffupdDelete(req)){
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
        log.info("============= End API CompareDetailStaffupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
