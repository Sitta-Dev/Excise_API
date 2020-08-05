package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.CompareNoticeDAO;
import com.xcs.phase2.model.compare.CompareNotice;
import com.xcs.phase2.request.compare.CompareNoticegetByArrestIDReq;
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
public class CompareNoticeController {

    private static final Logger log = LoggerFactory.getLogger(CompareNoticeController.class);

    @Autowired
    private CompareNoticeDAO compareNoticeDAO;

    @PostMapping(value = "/CompareNoticegetByArrestID")
    public ResponseEntity CompareNoticegetByArrestID(@RequestBody CompareNoticegetByArrestIDReq req) {

        log.info("============= Start API CompareNoticegetByArrestID ================");
        MessageResponse msg = new MessageResponse();
        List<CompareNotice> res = null;
        Boolean checkType = true;
        try {

            res = compareNoticeDAO.CompareNoticegetByArrestID(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CompareNoticegetByArrestID =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
