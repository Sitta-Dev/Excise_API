package com.xcs.phase2.controller.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.adjust.AdjustCompareDetailDAO;
import com.xcs.phase2.model.adjust.AdjustCompareDetail;
import com.xcs.phase2.request.adjust.AdjustCompareDetailupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.adjust.AdjustCompareDetailinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdjustCompareDetailController {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareDetailController.class);

    @Autowired
    private AdjustCompareDetailDAO adjustCompareDetailDAO;

    @PostMapping(value = "/AdjustCompareDetailinsAll")
    public ResponseEntity AdjustCompareDetailinsAll(@RequestBody AdjustCompareDetail req) {

        log.info("============= Start API AdjustCompareDetailinsAll ================");
        AdjustCompareDetailinsAllResponse res = null;
        try {

            res = adjustCompareDetailDAO.AdjustCompareDetailinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API AdjustCompareDetailinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareDetailupdByCon")
    public ResponseEntity AdjustCompareDetailupdByCon(@RequestBody AdjustCompareDetail req) {

        log.info("============= Start API AdjustCompareDetailupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(adjustCompareDetailDAO.AdjustCompareDetailupdByCon(req)){
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
        log.info("============= End API AdjustCompareDetailupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/AdjustCompareDetailupdDelete")
    public ResponseEntity AdjustCompareDetailupdDelete(@RequestBody AdjustCompareDetailupdDeleteReq req) {

        log.info("============= Start API AdjustCompareDetailupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(adjustCompareDetailDAO.AdjustCompareDetailupdDelete(req)){
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
        log.info("============= End API AdjustCompareDetailupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
