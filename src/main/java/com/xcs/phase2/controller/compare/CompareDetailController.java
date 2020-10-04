package com.xcs.phase2.controller.compare;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.CompareDetailDAO;
import com.xcs.phase2.model.compare.CompareDetail;
import com.xcs.phase2.request.compare.CompareDetailCheckReceriptReq;
import com.xcs.phase2.request.compare.CompareDetailupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.compare.CompareDetailCheckReceriptResponse;
import com.xcs.phase2.response.compare.CompareDetailinsAllResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompareDetailController {

    private static final Logger log = LoggerFactory.getLogger(CompareDetailController.class);

    @Autowired
    private CompareDetailDAO compareDetailDAO;

    @PostMapping(value = "/CompareDetailinsAll")
    public ResponseEntity CompareDetailinsAll(@RequestBody CompareDetail req) {

        log.info("============= Start API CompareDetailinsAll ================");
        CompareDetailinsAllResponse res = null;
        try {

            res = compareDetailDAO.CompareDetailinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API CompareDetailinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareDetailupdByCon")
    public ResponseEntity CompareDetailupdByCon(@RequestBody CompareDetail req) {

        log.info("============= Start API CompareDetailupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailDAO.CompareDetailupdByCon(req)){
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
        log.info("============= End API CompareDetailupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareDetailupdDelete")
    public ResponseEntity CompareDetailupdDelete(@RequestBody CompareDetailupdDeleteReq req) {

        log.info("============= Start API CompareDetailupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDetailDAO.CompareDetailupdDelete(req)){
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
        log.info("============= End API CompareDetailupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
    
    @PostMapping(value = "/CompareDetailCheckReceriptNo")
    public ResponseEntity CompareDetailCheckReceriptNo(@RequestBody CompareDetailCheckReceriptReq req) {

        log.info("============= Start API CompareDetailCheckReceriptNo ================");
        MessageResponse msg = new MessageResponse();
        CompareDetailCheckReceriptResponse res = null;
        try {

            res = compareDetailDAO.CompareDetailCheckReceriptNo(req);

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API CompareDetailCheckReceriptNo =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

}
