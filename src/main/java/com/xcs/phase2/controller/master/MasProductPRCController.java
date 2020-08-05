package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductPRCDAO;
import com.xcs.phase2.model.master.MasProductPRC;
import com.xcs.phase2.request.master.MasProductPRCgetByConReq;
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
public class MasProductPRCController {

    private static final Logger log = LoggerFactory.getLogger(MasProductSizeController.class);

    @Autowired
    private MasProductPRCDAO masProductPRCDAO;

    @PostMapping(value = "/MasProductPRCgetByCon")
    public ResponseEntity MasProductPRCgetByCon(@RequestBody MasProductPRCgetByConReq req) {

        log.info("============= Start API MasProductPRCgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductPRC> res = null;
        Boolean checkType = true;
        try {

            res = masProductPRCDAO.MasProductPRCgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductPRCgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
