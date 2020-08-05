package com.xcs.phase2.controller.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.investigate.InvestigateDetailSuspectDAO;
import com.xcs.phase2.model.investigate.InvestigateDetailSuspect;
import com.xcs.phase2.request.investigate.InvestigateDetailSuspectupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.investigate.InvestigateDetailSuspectinsAllResponse;
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
public class InvestigateDetailSuspectController {

    private static final Logger log = LoggerFactory.getLogger(InvestigateDetailSuspectController.class);

    @Autowired
    private InvestigateDetailSuspectDAO investigateDetailSuspectDAO;



    @PostMapping(value = "/InvestigateDetailSuspectinsAll")
    public ResponseEntity InvestigateDetailSuspectinsAll(@RequestBody List<InvestigateDetailSuspect> req) {

        log.info("============= Start API InvestigateDetailSuspectinsAll ================");
        InvestigateDetailSuspectinsAllResponse res = null;
        try {

            res = investigateDetailSuspectDAO.InvestigateDetailSuspectinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API InvestigateDetailSuspectinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailSuspectupdDelete")
    public ResponseEntity InvestigateDetailSuspectupdDelete(@RequestBody List<InvestigateDetailSuspectupdDeleteReq> req) {

        log.info("============= Start API InvestigateDetailSuspectupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(investigateDetailSuspectDAO.InvestigateDetailSuspectupdDelete(req)){
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
        log.info("============= End API InvestigateDetailSuspectupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
