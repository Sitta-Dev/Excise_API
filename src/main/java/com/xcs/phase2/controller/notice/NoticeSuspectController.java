package com.xcs.phase2.controller.notice;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.notice.NoticeSuspectDAO;
import com.xcs.phase2.model.notice.NoticeSuspect;
import com.xcs.phase2.request.notice.NoticeSuspectupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.notice.NoticeSupectinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeSuspectController {

    private static final Logger log = LoggerFactory.getLogger(NoticeSuspectController.class);

    @Autowired
    private NoticeSuspectDAO noticeSuspectDAO;

    @PostMapping(value = "/NoticeSupectinsAll")
    public ResponseEntity NoticeSupectinsAll(@RequestBody NoticeSuspect req) {

        log.info("============= Start API NoticeSupectinsAll ================");
        NoticeSupectinsAllResponse res = null;
        try {

            res = noticeSuspectDAO.NoticeSupectinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API NoticeSupectinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/NoticeSuspectupdDelete")
    public ResponseEntity NoticeSuspectupdDelete(@RequestBody NoticeSuspectupdDeleteReq req) {

        log.info("============= Start API NoticeSuspectupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(noticeSuspectDAO.NoticeSuspectupdDelete(req)){
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
        log.info("============= End API NoticeSuspectupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
