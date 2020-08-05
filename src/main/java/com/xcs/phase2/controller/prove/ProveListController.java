package com.xcs.phase2.controller.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveListDAO;
import com.xcs.phase2.model.prove.ProveList;
import com.xcs.phase2.request.prove.ProveListgetByConAdvReq;
import com.xcs.phase2.request.prove.ProveListgetByKeywordReq;
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
public class ProveListController {

    private static final Logger log = LoggerFactory.getLogger(ProveListController.class);

    @Autowired
    private ProveListDAO proveListDAO;

    @PostMapping(value = "/ProveListgetByKeyword")
    public ResponseEntity ProveListgetByKeyword(@RequestBody ProveListgetByKeywordReq req) {

        log.info("============= Start API ProveListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<ProveList> res = null;
        Boolean checkType = true;
        try {

            res = proveListDAO.ProveListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveListgetByConAdv")
    public ResponseEntity ProveListgetByConAdv(@RequestBody ProveListgetByConAdvReq req) {

        log.info("============= Start API ProveListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<ProveList> res = null;
        Boolean checkType = true;
        try {

            res = proveListDAO.ProveListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
