package com.xcs.phase2.controller.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.investigate.InvestigateDetailLocaleDAO;
import com.xcs.phase2.model.investigate.InvestigateDetailLocale;
import com.xcs.phase2.request.investigate.InvestigateDetailLocalupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.investigate.InvestigateDetailLocalinsAllResponse;
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
public class InvestigateDetailLocaleController {

    private static final Logger log = LoggerFactory.getLogger(InvestigateDetailLocaleController.class);

    @Autowired
    private InvestigateDetailLocaleDAO investigateDetailLocaleDAO;



    @PostMapping(value = "/InvestigateDetailLocalinsAll")
    public ResponseEntity InvestigateDetailLocalinsAll(@RequestBody List<InvestigateDetailLocale> req) {

        log.info("============= Start API InvestigateDetailLocalinsAll ================");
        InvestigateDetailLocalinsAllResponse res = null;
        try {

            res = investigateDetailLocaleDAO.InvestigateDetailLocalinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API InvestigateDetailLocalinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailLocalupdByCon")
    public ResponseEntity InvestigateDetailLocalupdByCon(@RequestBody List<InvestigateDetailLocale> req) {

        log.info("============= Start API InvestigateDetailLocalupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(investigateDetailLocaleDAO.InvestigateDetailLocalupdByCon(req)){
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
        log.info("============= End API InvestigateDetailLocalupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailLocalupdDelete")
    public ResponseEntity InvestigateDetailLocalupdDelete(@RequestBody List<InvestigateDetailLocalupdDeleteReq> req) {

        log.info("============= Start API InvestigateDetailLocalupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(investigateDetailLocaleDAO.InvestigateDetailLocalupdDelete(req)){
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
        log.info("============= End API InvestigateDetailLocalupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
