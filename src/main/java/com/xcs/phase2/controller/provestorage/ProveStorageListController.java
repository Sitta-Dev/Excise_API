package com.xcs.phase2.controller.provestorage;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.provestorage.ProveStorageListDAO;
import com.xcs.phase2.model.provestorage.ProveStorageList;
import com.xcs.phase2.request.provestorage.ProvestorageListgetByConAdvReq;
import com.xcs.phase2.request.provestorage.ProvestorageListgetByKeywordReq;
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
public class ProveStorageListController {

    private static final Logger log = LoggerFactory.getLogger(ProveStorageListController.class);

    @Autowired
    private ProveStorageListDAO proveStorageListDAO;

    @PostMapping(value = "/ProvestorageListgetByKeyword")
    public ResponseEntity ProvestorageListgetByKeyword(@RequestBody ProvestorageListgetByKeywordReq req) {

        log.info("============= Start API ProvestorageListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<ProveStorageList> res = null;
        Boolean checkType = true;
        try {

            res = proveStorageListDAO.ProvestorageListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProvestorageListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProvestorageListgetByConAdv")
    public ResponseEntity ProvestorageListgetByConAdv(@RequestBody ProvestorageListgetByConAdvReq req) {

        log.info("============= Start API ProvestorageListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<ProveStorageList> res = null;
        Boolean checkType = true;
        try {

            res = proveStorageListDAO.ProvestorageListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProvestorageListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

}
