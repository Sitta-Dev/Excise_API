package com.xcs.phase2.controller.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasLawGroupSubSectionRuleDAO;
import com.xcs.phase2.model.master.MasLawGroupSubSectionRule;
import com.xcs.phase2.model.master.New_MasLawGroupSection;
import com.xcs.phase2.request.master.MasLawGroupSubSectionRulegetByConReq;
import com.xcs.phase2.request.master.MasLawGroupSubSectionRulegetByKeywordReq;
import com.xcs.phase2.request.master.MasLawGroupSubSectionRuleupdDeleteReq;
import com.xcs.phase2.request.master.New_MasLawGroupSubSectionRulegetByConAdvReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasLawGroupSubSectionRuleinsAllResponse;
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
public class MasLawGroupSubSectionRuleController {

    private static final Logger log = LoggerFactory.getLogger(MasLawGroupSubSectionRuleController.class);

    @Autowired
    private MasLawGroupSubSectionRuleDAO masLawGroupSubSectionRuleDAO;

    @PostMapping(value = "/MasLawGroupSubSectionRulegetByKeyword")
    public ResponseEntity MasLawGroupSubSectionRulegetByKeyword(@RequestBody MasLawGroupSubSectionRulegetByKeywordReq req) {

        log.info("============= Start API MasLawGroupSubSectionRulegetByKeyword ================");
        MessageResponse msg = new MessageResponse();
        List<MasLawGroupSubSectionRule> res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupSubSectionRuleDAO.MasLawGroupSubSectionRulegetByKeyword(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupSubSectionRulegetByKeyword =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

//    @PostMapping(value = "/MasLawGroupSubSectionRulegetByConAdv")
//    public ResponseEntity MasLawGroupSubSectionRulegetByConAdv(@RequestBody MasLawGroupSubSectionRulegetByConAdvReq req) {
//
//        log.info("============= Start API MasLawGroupSubSectionRulegetByConAdv ================");
//        MessageResponse msg = new MessageResponse();
//        List<MasLawGroupSubSectionRule> res = null;
//        Boolean checkType = true;
//        try {
//
//            res = masLawGroupSubSectionRuleDAO.MasLawGroupSubSectionRulegetByConAdv(req);
//
//        } catch (Exception e) {
//            checkType = false;
//            msg.setIsSuccess(Message.FALSE);
//            msg.setMsg(e.getMessage());
//
//        }
//        log.info("============= End API MasLawGroupSubSectionRulegetByConAdv =================");
//        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
//    }

    @PostMapping(value = "/MasLawGroupSubSectionRulegetByConAdv")
    public ResponseEntity MasLawGroupSubSectionRulegetByConAdv(@RequestBody New_MasLawGroupSubSectionRulegetByConAdvReq req) {

        log.info("============= Start API MasLawGroupSubSectionRulegetByConAdv ================");
        MessageResponse msg = new MessageResponse();
        List<New_MasLawGroupSection> res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupSubSectionRuleDAO.New_MasLawGroupSubSectionRulegetByConAdv(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupSubSectionRulegetByConAdv =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSubSectionRulegetByCon")
    public ResponseEntity MasLawGroupSubSectionRulegetByCon(@RequestBody MasLawGroupSubSectionRulegetByConReq req) {


        log.info("============= Start API MasLawGroupSubSectionRulegetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasLawGroupSubSectionRule res = null;
        Boolean checkType = true;
        try {

            res = masLawGroupSubSectionRuleDAO.MasLawGroupSubSectionRulegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupSubSectionRulegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSubSectionRuleinsAll")
    public ResponseEntity MasLawGroupSubSectionRuleinsAll(@RequestBody MasLawGroupSubSectionRule req) {

        log.info("============= Start API MasLawGroupSubSectionRuleinsAll ================");
        MasLawGroupSubSectionRuleinsAllResponse res = null;
        try {

            res = masLawGroupSubSectionRuleDAO.MasLawGroupSubSectionRuleinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API MasLawGroupSubSectionRuleinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSubSectionRuleupdAll")
    public ResponseEntity MasLawGroupSubSectionRuleupdAll(@RequestBody MasLawGroupSubSectionRule req) {

        log.info("============= Start API MasLawGroupSubSectionRuleupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawGroupSubSectionRuleDAO.MasLawGroupSubSectionRuleupdAll(req)){
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
        log.info("============= End API MasLawGroupSubSectionRuleupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSubSectionRuleupdDelete")
    public ResponseEntity MasLawGroupSubSectionRuleupdDelete(@RequestBody MasLawGroupSubSectionRuleupdDeleteReq req) {

        log.info("============= Start API MasLawGroupSubSectionRuleupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawGroupSubSectionRuleDAO.MasLawGroupSubSectionRuleupdDelete(req)){
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
        log.info("============= End API MasLawGroupSubSectionRuleupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
