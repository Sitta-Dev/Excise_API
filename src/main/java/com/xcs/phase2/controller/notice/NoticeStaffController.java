package com.xcs.phase2.controller.notice;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.notice.NoticeStaffDAO;
import com.xcs.phase2.model.notice.NoticeStaff;
import com.xcs.phase2.request.notice.NoticeStaffupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.notice.NoticeStaffinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeStaffController {

    private static final Logger log = LoggerFactory.getLogger(NoticeStaffController.class);

    @Autowired
    private NoticeStaffDAO noticeStaffDAO;

    @PostMapping(value = "/NoticeStaffinsAll")
    public ResponseEntity NoticeStaffinsAll(@RequestBody NoticeStaff req) {

        log.info("============= Start API NoticeStaffinsAll ================");
        NoticeStaffinsAllResponse res = null;
        try {

            res = noticeStaffDAO.NoticeStaffinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API NoticeStaffinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/NoticeStaffupdByCon")
    public ResponseEntity NoticeStaffupdByCon(@RequestBody NoticeStaff req) {

        log.info("============= Start API NoticeStaffupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(noticeStaffDAO.NoticeStaffupdByCon(req)){
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
        log.info("============= End API NoticeStaffupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/NoticeStaffupdDelete")
    public ResponseEntity NoticeStaffupdDelete(@RequestBody NoticeStaffupdDeleteReq req) {

        log.info("============= Start API NoticeStaffupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(noticeStaffDAO.NoticeStaffupdDelete(req)){
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
        log.info("============= End API NoticeStaffupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
