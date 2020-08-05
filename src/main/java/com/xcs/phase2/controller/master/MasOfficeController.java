package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasOfficeDAO;
import com.xcs.phase2.request.master.MasOfficegetByConReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasOfficeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasOfficeController {

    private static final Logger log = LoggerFactory.getLogger(MasOfficeController.class);

    @Autowired
    private MasOfficeDAO masOfficeDAO;

//    @PostMapping(value = "/MasOfficegetByCon")
//    public ResponseEntity MasOfficegetByCon(@RequestBody MasOfficegetByConReq req) {
//
//        log.info("============= Start API MasOfficegetByCon ================");
//        MessageResponse msg = new MessageResponse();
//        List<MasOffice> res = null;
//        Boolean checkType = true;
//        try {
//
//            res = masOfficeDAO.MasOfficegetByCon(req);
//
//        } catch (Exception e) {
//            checkType = false;
//            msg.setIsSuccess(Message.FALSE);
//            msg.setMsg(e.getMessage());
//
//        }
//        log.info("============= End API MasOfficegetByCon =================");
//        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
//    }

    @PostMapping(value = "/MasOfficegetByCon")
    public ResponseEntity MasOfficegetByCon(@RequestBody MasOfficegetByConReq req) {

        log.info("============= Start API MasOfficegetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasOfficeResponse res = null;
        Boolean checkType = true;
        try {

            res = masOfficeDAO.MasOfficegetByConList(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasOfficegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

}
