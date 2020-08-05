package com.xcs.phase2.controller.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasLawGuiltbaseDAO;
import com.xcs.phase2.model.master.MasLawGuiltbase;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasLawGuiltbaseinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasLawGuiltbaseController {

    private static final Logger log = LoggerFactory.getLogger(MasLawGuiltbaseController.class);

    @Autowired
    private MasLawGuiltbaseDAO masLawGuiltbaseDAO;

    @PostMapping(value = "/MasLawGuiltbaseinsAll")
    public ResponseEntity MasLawGuiltbaseinsAll(@RequestBody MasLawGuiltbase req) {

        log.info("============= Start API MasLawGuiltbaseinsAll ================");
        MasLawGuiltbaseinsAllResponse res = null;
        try {

            res = masLawGuiltbaseDAO.MasLawGuiltbaseinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API MasLawGuiltbaseinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGuiltbaseupdAll")
    public ResponseEntity MasLawGuiltbaseupdAll(@RequestBody MasLawGuiltbase req) {

        log.info("============= Start API MasLawGuiltbaseupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawGuiltbaseDAO.MasLawGuiltbaseupdAll(req)){
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
        log.info("============= End API MasLawGuiltbaseupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }


}
