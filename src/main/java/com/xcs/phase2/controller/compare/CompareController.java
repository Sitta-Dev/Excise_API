package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.CompareDAO;
import com.xcs.phase2.model.compare.Compare;
import com.xcs.phase2.request.compare.CompareVerifyCompareNoReq;
import com.xcs.phase2.request.compare.ComparegetByConReq;
import com.xcs.phase2.request.compare.CompareupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.compare.CompareinsAllResponse;
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
public class CompareController {

    private static final Logger log = LoggerFactory.getLogger(CompareController.class);

    @Autowired
    private CompareDAO compareDAO;

    @PostMapping(value = "/ComparegetByCon")
    public ResponseEntity ComparegetByCon(@RequestBody ComparegetByConReq req) {


        log.info("============= Start API ComparegetByCon ================");
        MessageResponse msg = new MessageResponse();
        Compare res = null;
        Boolean checkType = true;
        try {

            res = compareDAO.ComparegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ComparegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareinsAll")
    public ResponseEntity CompareinsAll(@RequestBody Compare req) {

        log.info("============= Start API CompareinsAll ================");
        CompareinsAllResponse res = null;
        try {

            res = compareDAO.CompareinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API CompareinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareupdByCon")
    public ResponseEntity CompareupdByCon(@RequestBody Compare req) {

        log.info("============= Start API CompareupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDAO.CompareupdByCon(req)){
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
        log.info("============= End API CompareupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareupdDelete")
    public ResponseEntity CompareupdDelete(@RequestBody CompareupdDeleteReq req) {

        log.info("============= Start API CompareupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(compareDAO.CompareupdDelete(req)){
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
        log.info("============= End API CompareupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareVerifyCompareNo")
    public ResponseEntity CompareVerifyCompareNo(@RequestBody CompareVerifyCompareNoReq req) {

        log.info("============= Start API CompareVerifyCompareNo ================");
        MessageResponse msg = new MessageResponse();
        List<Compare> res = null;
        Boolean checkType = true;
        try {

            res = compareDAO.CompareVerifyCompareNo(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CompareVerifyCompareNo =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
