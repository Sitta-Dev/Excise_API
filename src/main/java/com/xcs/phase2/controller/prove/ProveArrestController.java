package com.xcs.phase2.controller.prove;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveArrestDAO;
import com.xcs.phase2.model.prove.NewProveArrest;
import com.xcs.phase2.request.prove.ProveArrestgetByConReq;
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
public class ProveArrestController {

    private static final Logger log = LoggerFactory.getLogger(ProveArrestController.class);

    @Autowired
    private ProveArrestDAO proveArrestDAO;

    @PostMapping(value = "/ProveArrestgetByCon")
    public ResponseEntity ProveArrestgetByCon(@RequestBody ProveArrestgetByConReq req) {

        log.info("============= Start API ProveArrestgetByCon ================");
        MessageResponse msg = new MessageResponse();
        NewProveArrest res = null;
        Boolean checkType = true;
        try {

            res = proveArrestDAO.NewProveArrestgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveArrestgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
