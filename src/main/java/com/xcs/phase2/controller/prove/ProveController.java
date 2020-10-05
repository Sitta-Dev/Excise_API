package com.xcs.phase2.controller.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveDAO;
import com.xcs.phase2.model.prove.Prove;
import com.xcs.phase2.model.prove.ProveCompare;
import com.xcs.phase2.request.prove.ProveComparegetByProveIDReq;
import com.xcs.phase2.request.prove.ProveVerifyProveNoReq;
import com.xcs.phase2.request.prove.ProvegetByConReq;
import com.xcs.phase2.request.prove.ProveupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.prove.CourtJudgmentResponse;
import com.xcs.phase2.response.prove.ProveinsAllResponse;
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
public class ProveController {

    private static final Logger log = LoggerFactory.getLogger(ProveController.class);

    @Autowired
    private ProveDAO proveDAO;

    @PostMapping(value = "/ProvegetByCon")
    public ResponseEntity ProvegetByCon(@RequestBody ProvegetByConReq req) {


        log.info("============= Start API ProvegetByCon ================");
        MessageResponse msg = new MessageResponse();
        Prove res = null;
        Boolean checkType = true;
        try {

            res = proveDAO.ProvegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProvegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveinsAll")
    public ResponseEntity ProveinsAll(@RequestBody Prove req) {

        log.info("============= Start API ProveinsAll ================");
        ProveinsAllResponse res = null;
        try {

            res = proveDAO.ProveinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API ProveinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveupdByCon")
    public ResponseEntity ProveupdByCon(@RequestBody Prove req) {

        log.info("============= Start API ProveupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveDAO.ProveupdByCon(req)){
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
        log.info("============= End API ProveupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveupdDelete")
    public ResponseEntity ProveupdByCon(@RequestBody ProveupdDeleteReq req) {

        log.info("============= Start API ProveupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(proveDAO.ProveupdDelete(req)){
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
        log.info("============= End API ProveupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveVerifyProveNo")
    public ResponseEntity ProveVerifyProveNo(@RequestBody ProveVerifyProveNoReq req) {

        log.info("============= Start API ProveVerifyProveNo ================");
        MessageResponse msg = new MessageResponse();
        List<Prove> res = null;
        Boolean checkType = true;
        try {

            res = proveDAO.ProveVerifyProveNo(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveVerifyProveNo =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveComparegetByProveID")
    public ResponseEntity ProveComparegetByProveID(@RequestBody ProveComparegetByProveIDReq req) {

        log.info("============= Start API ProveComparegetByProveID ================");
        MessageResponse msg = new MessageResponse();
        ProveCompare res = null;
        Boolean checkType = true;
        try {

            res = proveDAO.ProveComparegetByProveID(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveComparegetByProveID =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
    
    @PostMapping(value = "/ProveVerifyCourtJudgment")
    public ResponseEntity ProveVerifyCourtJudgment(@RequestBody ProvegetByConReq req) {

        log.info("============= Start API ProveVerifyCourtJudgment ================");
        MessageResponse msg = new MessageResponse();
        List<CourtJudgmentResponse> res = null;
        Boolean checkType = true;
        try {

            res = proveDAO.ProveVerifyCourtJudgment(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveVerifyCourtJudgment =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
