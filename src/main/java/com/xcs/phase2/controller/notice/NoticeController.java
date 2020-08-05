package com.xcs.phase2.controller.notice;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.notice.NoticeDAO;
import com.xcs.phase2.model.notice.Notice;
import com.xcs.phase2.request.notice.NoticegetByConReq;
import com.xcs.phase2.request.notice.NoticeupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.notice.NoticeinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    private static final Logger log = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeDAO noticeDAO;

    @PostMapping(value = "/NoticeinsAll")
    public ResponseEntity NoticeinsAll(@RequestBody Notice req) { //**

        log.info("============= Start API NoticeinsAll ================");
        NoticeinsAllResponse res = null;
        try {

            res = noticeDAO.NoticeinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API NoticeinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/NoticeupdByCon")
    public ResponseEntity NoticeupdByCon(@RequestBody Notice req) {

        log.info("============= Start API NoticeupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(noticeDAO.NoticeupdByCon(req)){
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
        log.info("============= End API NoticeupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/NoticeupdDelete")
    public ResponseEntity NoticeupdDelete(@RequestBody NoticeupdDeleteReq req) {

        log.info("============= Start API NoticeupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(noticeDAO.NoticeupdDelete(req)){
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
        log.info("============= End API NoticeupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/NoticegetByCon")
    public ResponseEntity NoticegetByCon(@RequestBody NoticegetByConReq req) {


        log.info("============= Start API NoticegetByCon ================");
        MessageResponse msg = new MessageResponse();
        Notice res = null;
        Boolean checkType = true;
        try {

            res = noticeDAO.NoticegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API NoticegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
