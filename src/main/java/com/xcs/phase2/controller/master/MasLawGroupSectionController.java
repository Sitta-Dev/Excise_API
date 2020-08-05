package com.xcs.phase2.controller.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasLawGroupSectionDAO;
import com.xcs.phase2.model.master.MasLaw;
import com.xcs.phase2.model.master.MasLawGroupSection;
import com.xcs.phase2.request.master.MasLawGroupSectiongetByConAdvReq;
import com.xcs.phase2.request.master.MasLawGroupSectiongetByConReq;
import com.xcs.phase2.request.master.MasLawGroupSectiongetByKeywordReq;
import com.xcs.phase2.request.master.MasLawGroupSectionupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasLawGroupSectioninsAllResponse;
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
public class MasLawGroupSectionController {

    private static final Logger log = LoggerFactory.getLogger(MasOfficeController.class);

    @Autowired
    private MasLawGroupSectionDAO masLawGroupSectionDAO;

    @PostMapping(value = "/MasLawGroupSectiongetByKeyword")
    public ResponseEntity MasLawGroupSectiongetByKeyword(@RequestBody MasLawGroupSectiongetByKeywordReq req) {

        log.info("============= Start API MasLawGroupSectiongetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<MasLaw> res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupSectionDAO.MasLawGroupSectiongetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupSectiongetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSectiongetByConAdv")
    public ResponseEntity MasLawGroupSectiongetByConAdv(@RequestBody MasLawGroupSectiongetByConAdvReq req) {

        log.info("============= Start API MasLawGroupSectiongetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<MasLaw> res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupSectionDAO.MasLawGroupSectiongetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupSectiongetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSectiongetByCon")
    public ResponseEntity MasLawGroupSectiongetByCon(@RequestBody MasLawGroupSectiongetByConReq req) {


        log.info("============= Start API MasLawGroupSectiongetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasLawGroupSection res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupSectionDAO.MasLawGroupSectiongetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupSectiongetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSectioninsAll")
    public ResponseEntity MasLawGroupSectioninsAll(@RequestBody MasLawGroupSection req) {

        log.info("============= Start API MasLawGroupSectioninsAll ================");
        MasLawGroupSectioninsAllResponse res = null;
        try {

            res = masLawGroupSectionDAO.MasLawGroupSectioninsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API MasLawGroupSectioninsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSectionpupdAll")
    public ResponseEntity MasLawGroupSectionpupdAll(@RequestBody MasLawGroupSection req) {

        log.info("============= Start API MasLawGroupSectionpupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawGroupSectionDAO.MasLawGroupSectionpupdAll(req)){
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
        log.info("============= End API MasLawGroupSectionpupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSectionupdDelete")
    public ResponseEntity MasLawGroupSectionupdDelete(@RequestBody MasLawGroupSectionupdDeleteReq req) {

        log.info("============= Start API MasLawGroupSectionupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawGroupSectionDAO.MasLawGroupSectionupdDelete(req)){
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
        log.info("============= End API MasLawGroupSectionupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
