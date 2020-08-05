package com.xcs.phase2.controller.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.investigate.InvestigateDAO;
import com.xcs.phase2.model.investigate.Investigate;
import com.xcs.phase2.request.investigate.InvestigategetByConReq;
import com.xcs.phase2.request.investigate.InvestigateupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.investigate.InvestigateinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestigateController {

    private static final Logger log = LoggerFactory.getLogger(InvestigateController.class);

    @Autowired
    private InvestigateDAO investigateDAO;

    @PostMapping(value = "/InvestigategetByCon")
    public ResponseEntity InvestigategetByCon(@RequestBody InvestigategetByConReq req) {

        log.info("============= Start API InvestigategetByCon ================");
        MessageResponse msg = new MessageResponse();
        Investigate res = null;
        Boolean checkType = true;
        try {

            res = investigateDAO.InvestigategetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API InvestigategetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateinsAll")
    public ResponseEntity InvestigateinsAll(@RequestBody Investigate req) {

        log.info("============= Start API InvestigateinsAll ================");
        InvestigateinsAllResponse res = null;
        try {

            res = investigateDAO.InvestigateinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API InvestigateinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateupdByCon")
    public ResponseEntity InvestigateupdByCon(@RequestBody Investigate req) {

        log.info("============= Start API InvestigateupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(investigateDAO.InvestigateupdByCon(req)){
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
        log.info("============= End API InvestigateupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateupdDelete")
    public ResponseEntity InvestigateupdDelete(@RequestBody InvestigateupdDeleteReq req) {

        log.info("============= Start API InvestigateupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(investigateDAO.InvestigateupdDelete(req)){
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
        log.info("============= End API InvestigateupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
