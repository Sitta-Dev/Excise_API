package com.xcs.phase2.controller.notice;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.notice.NoticeListDAO;
import com.xcs.phase2.model.notice.NoticeList;
import com.xcs.phase2.request.notice.NoticeListgetByConAdvReq;
import com.xcs.phase2.request.notice.NoticeListgetByKeywordReq;
import com.xcs.phase2.response.MessageResponse;
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
public class NoticeListController {

    private static final Logger log = LoggerFactory.getLogger(NoticeListController.class);

    @Autowired
    private NoticeListDAO noticeListDAO;

    @PostMapping(value = "/NoticeListgetByKeyword")
    public ResponseEntity NoticeListgetByKeyword(@RequestBody NoticeListgetByKeywordReq req) {

        log.info("============= Start API NoticeListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<NoticeList> res = null;
        Boolean checkType = true;
        try {

            res = noticeListDAO.NoticeListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API NoticeListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/NoticeListgetByConAdv")
    public ResponseEntity NoticeListgetByConAdv(@RequestBody NoticeListgetByConAdvReq req) {

        log.info("============= Start API NoticeListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<NoticeList> res = null;
        Boolean checkType = true;
        try {

            res = noticeListDAO.NoticeListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API NoticeListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
