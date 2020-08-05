package com.xcs.phase2.controller.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasLawGroupSubSectionDAO;
import com.xcs.phase2.model.master.MasLawGroupSubSection;
import com.xcs.phase2.request.master.MasLawGroupSubSectiongetByConReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasLawGroupSubSectioninsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasLawGroupSubSectionController {

    private static final Logger log = LoggerFactory.getLogger(MasLawGroupSubSectionController.class);

    @Autowired
    private MasLawGroupSubSectionDAO masLawGroupSubSectionDAO;



    @PostMapping(value = "/MasLawGroupSubSectioninsAll")
    public ResponseEntity MasLawGroupSubSectioninsAll(@RequestBody MasLawGroupSubSection req) {

        log.info("============= Start API MasLawGroupSubSectioninsAll ================");
        MasLawGroupSubSectioninsAllResponse res = null;
        try {

            res = masLawGroupSubSectionDAO.MasLawGroupSubSectioninsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API MasLawGroupSubSectioninsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSubSectionupdAll")
    public ResponseEntity MasLawGroupSubSectionupdAll(@RequestBody MasLawGroupSubSection req) {

        log.info("============= Start API MasLawGroupSubSectionupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawGroupSubSectionDAO.MasLawGroupSubSectionupdAll(req)){
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
        log.info("============= End API MasLawGroupSubSectionupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSubSectiongetByCon")
    public ResponseEntity MasLawGroupSubSectiongetByCon(@RequestBody MasLawGroupSubSectiongetByConReq req) {


        log.info("============= Start API MasLawGroupSubSectiongetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasLawGroupSubSection res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupSubSectionDAO.MasLawGroupSubSectiongetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupSubSectiongetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }


}
