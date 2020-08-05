package com.xcs.phase2.controller.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasLawGroupDAO;
import com.xcs.phase2.model.master.MasLawGroup;
import com.xcs.phase2.request.master.*;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasLawGroupinsAllResponse;
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
public class MasLawGroupController {

    private static final Logger log = LoggerFactory.getLogger(MasOfficeController.class);

    @Autowired
    private MasLawGroupDAO masLawGroupDAO;

    @PostMapping(value = "/MasLawGroupgetByKeyword")
    public ResponseEntity MasLawGroupgetByKeyword(@RequestBody MasLawGroupgetByKeywordReq req) {

        log.info("============= Start API MasLawGroupgetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<MasLawGroup> res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupDAO.MasLawGroupgetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupgetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupgetByConAdv")
    public ResponseEntity MasLawGroupgetByConAdv(@RequestBody MasLawGroupgetByConAdvReq req) {

        log.info("============= Start API MasLawGroupgetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<MasLawGroup> res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupDAO.MasLawGroupgetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupgetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupgetByCon")
    public ResponseEntity MasLawGroupgetByCon(@RequestBody MasLawGroupgetByConReq req) {


        log.info("============= Start API MasLawGroupgetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasLawGroup res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupDAO.MasLawGroupgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupinsAll")
    public ResponseEntity MasLawGroupinsAll(@RequestBody MasLawGroup req) {

        log.info("============= Start API MasLawGroupinsAll ================");
        MasLawGroupinsAllResponse res = null;
        try {

            res = masLawGroupDAO.MasLawGroupinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API MasLawGroupinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/MasMasLawGroupupdAll")
    public ResponseEntity MasMasLawGroupupdAll(@RequestBody MasLawGroup req) {

        log.info("============= Start API MasMasLawGroupupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawGroupDAO.MasMasLawGroupupdAll(req)){
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
        log.info("============= End API MasMasLawGroupupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupupdDelete")
    public ResponseEntity MasLawGroupupdDelete(@RequestBody MasLawGroupupdDeleteReq req) {

        log.info("============= Start API MasLawGroupupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawGroupDAO.MasLawGroupupdDelete(req)){
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
        log.info("============= End API MasLawGroupupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
