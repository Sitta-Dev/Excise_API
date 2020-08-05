package com.xcs.phase2.controller.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.investigate.InvestigateDetail;
import com.xcs.phase2.request.investigate.InvestigateDetailgetByConReq;
import com.xcs.phase2.request.investigate.InvestigateDetailupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.investigate.InvestigateDetailinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestigateDetailController {

    private static final Logger log = LoggerFactory.getLogger(InvestigateDetailController.class);

    @Autowired
    private com.xcs.phase2.dao.investigate.InvestigateDetailDAO InvestigateDetailDAO;

    @PostMapping(value = "/InvestigateDetailgetByCon")
    public ResponseEntity InvestigateDetailgetByCon(@RequestBody InvestigateDetailgetByConReq req) {

        log.info("============= Start API InvestigateDetailgetByCon ================");
        MessageResponse msg = new MessageResponse();
        InvestigateDetail res = null;
        Boolean checkType = true;
        try {

            res = InvestigateDetailDAO.InvestigateDetailgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API InvestigateDetailgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailinsAll")
    public ResponseEntity InvestigateDetailinsAll(@RequestBody InvestigateDetail req) {

        log.info("============= Start API InvestigateDetailinsAll ================");
        InvestigateDetailinsAllResponse res = null;
        try {

            res = InvestigateDetailDAO.InvestigateDetailinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API InvestigateDetailinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailupdByCon")
    public ResponseEntity InvestigateDetailupdByCon(@RequestBody InvestigateDetail req) {

        log.info("============= Start API InvestigateDetailupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(InvestigateDetailDAO.InvestigateDetailupdByCon(req)){
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
        log.info("============= End API InvestigateDetailupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailupdDelete")
    public ResponseEntity InvestigateDetailupdDelete(@RequestBody InvestigateDetailupdDeleteReq req) {

        log.info("============= Start API InvestigateDetailupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(InvestigateDetailDAO.InvestigateDetailupdDelete(req)){
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
        log.info("============= End API InvestigateDetailupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
