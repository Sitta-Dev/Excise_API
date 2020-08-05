package com.xcs.phase2.controller.uac;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.EdOfficeDAO;
import com.xcs.phase2.model.uac.EdOffice;
import com.xcs.phase2.request.uac.EDOfficeAreagetbyConReq;
import com.xcs.phase2.request.uac.EDOfficeBranchgetbyConReq;
import com.xcs.phase2.request.uac.EDOfficeDepartmentgetbyConReq;
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
public class EdOfficeController {

    private static final Logger log = LoggerFactory.getLogger(EdOfficeController.class);

    @Autowired
    private EdOfficeDAO edOfficeDAO;

    @PostMapping(value = "/EDOfficeDepartmentgetbyCon")
    public ResponseEntity EDOfficeDepartmentgetbyCon(@RequestBody EDOfficeDepartmentgetbyConReq req) {

        log.info("============= Start API EDOfficeDepartmentgetbyCon ================");
        MessageResponse msg = new MessageResponse();
        List<EdOffice> res = null;
        Boolean checkType = true;
        try {

            res = edOfficeDAO.EDOfficeDepartmentgetbyCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EDOfficeDepartmentgetbyCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EDOfficeAreagetbyCon")
    public ResponseEntity EDOfficeAreagetbyCon(@RequestBody EDOfficeAreagetbyConReq req) {

        log.info("============= Start API EDOfficeAreagetbyCon ================");
        MessageResponse msg = new MessageResponse();
        List<EdOffice> res = null;
        Boolean checkType = true;
        try {

            res = edOfficeDAO.EDOfficeAreagetbyCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EDOfficeAreagetbyCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EDOfficeBranchgetbyCon")
    public ResponseEntity EDOfficeBranchgetbyCon(@RequestBody EDOfficeBranchgetbyConReq req) {

        log.info("============= Start API EDOfficeBranchgetbyCon ================");
        MessageResponse msg = new MessageResponse();
        List<EdOffice> res = null;
        Boolean checkType = true;
        try {

            res = edOfficeDAO.EDOfficeBranchgetbyCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EDOfficeBranchgetbyCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/EDOfficeDepartmentgetAll")
    public ResponseEntity EDOfficeDepartmentgetAll() {

        log.info("============= Start API EDOfficeDepartmentgetAll ================");
        MessageResponse msg = new MessageResponse();
        List<EdOffice> res = null;
        Boolean checkType = true;
        try {

            res = edOfficeDAO.EDOfficeDepartmentgetAll();

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API EDOfficeDepartmentgetAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
