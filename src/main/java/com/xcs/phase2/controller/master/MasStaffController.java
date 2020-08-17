package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasStaffDAO;
import com.xcs.phase2.model.master.MasStaff;
import com.xcs.phase2.request.master.MasStaffgetByConAdvReq;
import com.xcs.phase2.request.master.MasStaffgetByConReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasStaffgetByConResponse;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasStaffController {

    private static final Logger log = LoggerFactory.getLogger(MasStaffController.class);

    @Autowired
    private MasStaffDAO masStaffDAO;

    @PostMapping(value = "/MasStaffgetByCon")
    public ResponseEntity MasStaffgetByCon(@RequestBody MasStaffgetByConReq req) {

        log.info("============= Start API MasStaffgetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasStaffgetByConResponse res = null;
        Boolean checkType = true;
        try {

            res = masStaffDAO.MasStaffgetByConNew(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasStaffgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
    
    @PostMapping(value = "/MasStaffgetByConAdv")
    public ResponseEntity MasStaffgetByConAdv(@RequestBody MasStaffgetByConAdvReq req) {

        log.info("============= Start API MasStaffgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<MasStaff> res = null;
        Boolean checkType = true;
        try {

            res = masStaffDAO.MasStaffgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasStaffgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

}
