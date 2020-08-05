package com.xcs.phase2.controller.notice;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.notice.NoticeProductDAO;
import com.xcs.phase2.model.notice.NoticeProduct;
import com.xcs.phase2.request.notice.NoticeProductupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.notice.NoticeProductinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeProductController {

    private static final Logger log = LoggerFactory.getLogger(NoticeProductController.class);

    @Autowired
    private NoticeProductDAO noticeProductDAO;

    @PostMapping(value = "/NoticeProductinsAll")
    public ResponseEntity NoticeProductinsAll(@RequestBody NoticeProduct req) {

        log.info("============= Start API NoticeProductinsAll ================");
        NoticeProductinsAllResponse res = null;
        try {

            res = noticeProductDAO.NoticeProductinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API NoticeProductinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/NoticeProductupdByCon")
    public ResponseEntity NoticeProductupdByCon(@RequestBody NoticeProduct req) {

        log.info("============= Start API NoticeProductupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(noticeProductDAO.NoticeProductupdByCon(req)){
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
        log.info("============= End API NoticeProductupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/NoticeProductupdDelete")
    public ResponseEntity NoticeProductupdDelete(@RequestBody NoticeProductupdDeleteReq req) {

        log.info("============= Start API NoticeProductupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(noticeProductDAO.NoticeProductupdDelete(req)){
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
        log.info("============= End API NoticeProductupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
