package com.xcs.phase2.controller.adjust;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.adjust.AdjustCompareDAO;
import com.xcs.phase2.model.adjust.AdjustCompare;
import com.xcs.phase2.request.adjust.AdjustComparegetByConReq;
import com.xcs.phase2.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdjustCompareController {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareController.class);

    @Autowired
    private AdjustCompareDAO compareDAO;

    @PostMapping(value = "/AdjustComparegetByCon")
    public ResponseEntity AdjustComparegetByCon(@RequestBody AdjustComparegetByConReq req) {


        log.info("============= Start API AdjustComparegetByCon ================");
        MessageResponse msg = new MessageResponse();
        AdjustCompare res = null;
        Boolean checkType = true;
        try {

            res = compareDAO.AdjustComparegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API AdjustComparegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

}
