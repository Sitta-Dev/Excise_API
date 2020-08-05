package com.xcs.phase2.controller.target;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.target.TargetDetailDAO;
import com.xcs.phase2.model.target.TargetDetail;
import com.xcs.phase2.request.target.TargetDetailgetByConReq;
import com.xcs.phase2.response.MessageResponse;
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
public class TargetDetailController {

    private static final Logger log = LoggerFactory.getLogger(TargetDetailController.class);

    @Autowired
    private TargetDetailDAO targetDetailDAO;

    @PostMapping(value = "/TargetDetailgetByCon")
    public ResponseEntity TargetDetailgetByCon(@RequestBody TargetDetailgetByConReq req) {

        log.info("============= Start API TargetDetailgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<TargetDetail> res = null;
        Boolean checkType = true;
        try {

            res = targetDetailDAO.TargetDetailgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API TargetDetailgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
