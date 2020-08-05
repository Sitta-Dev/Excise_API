package com.xcs.phase2.controller.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasLawPenaltyDAO;
import com.xcs.phase2.model.master.MasLawPenalty;
import com.xcs.phase2.request.master.MasLawGroupSectionPenaltygetByConReq;
import com.xcs.phase2.request.master.MasLawPenaltygetByConReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasLawPenaltyinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasLawPenaltyController {

    private static final Logger log = LoggerFactory.getLogger(MasLawPenaltyController.class);

    @Autowired
    private MasLawPenaltyDAO masLawPenaltyDAO;


    @PostMapping(value = "/MasLawPenaltygetByCon")
    public ResponseEntity MasLawPenaltygetByCon(@RequestBody MasLawPenaltygetByConReq req) {


        log.info("============= Start API MasLawPenaltygetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasLawPenalty res = null;
        Boolean checkType = true;
        try {

            res = masLawPenaltyDAO.MasLawPenaltygetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawPenaltygetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGroupSectionPenaltygetByCon")
    public ResponseEntity MasLawGroupSectionPenaltygetByCon(@RequestBody MasLawGroupSectionPenaltygetByConReq req) {


        log.info("============= Start API MasLawGroupSectionPenaltygetByCon ================");
        MessageResponse msg = new MessageResponse();
        MasLawPenalty res = null;
        Boolean checkType = true;
        try {

            res = masLawPenaltyDAO.MasLawGroupSectionPenaltygetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGroupSectionPenaltygetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawPenaltyinsAll")
    public ResponseEntity MasLawPenaltyinsAll(@RequestBody MasLawPenalty req) {

        log.info("============= Start API MasLawPenaltyinsAll ================");
        MasLawPenaltyinsAllResponse res = null;
        try {

            res = masLawPenaltyDAO.MasLawPenaltyinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API MasLawPenaltyinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawPenaltyupdAll")
    public ResponseEntity MasLawPenaltyupdAll(@RequestBody MasLawPenalty req) {

        log.info("============= Start API MasLawPenaltyupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawPenaltyDAO.MasLawPenaltyupdAll(req)){
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
        log.info("============= End API MasLawPenaltyupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }


}
