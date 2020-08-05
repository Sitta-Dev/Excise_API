package com.xcs.phase2.controller.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.investigate.InvestigateListDAO;
import com.xcs.phase2.model.investigate.InvestigateList;
import com.xcs.phase2.request.investigate.InvestigateListgetByConAdvReq;
import com.xcs.phase2.request.investigate.InvestigateListgetByKeywordReq;
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
public class InvestigateListController {

    private static final Logger log = LoggerFactory.getLogger(InvestigateListController.class);

    @Autowired
    private InvestigateListDAO investigateListDAO;

    @PostMapping(value = "/InvestigateListgetByKeyword")
    public ResponseEntity InvestigateListgetByKeyword(@RequestBody InvestigateListgetByKeywordReq req) {

        log.info("============= Start API InvestigateListgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<InvestigateList> res = null;
        Boolean checkType = true;
        try {

            res = investigateListDAO.InvestigateListgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API InvestigateListgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateListgetByConAdv")
    public ResponseEntity InvestigateListgetByConAdv(@RequestBody InvestigateListgetByConAdvReq req) {

        log.info("============= Start API InvestigateListgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<InvestigateList> res = null;
        Boolean checkType = true;
        try {

            res = investigateListDAO.InvestigateListgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API InvestigateListgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
