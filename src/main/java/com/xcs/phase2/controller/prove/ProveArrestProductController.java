package com.xcs.phase2.controller.prove;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveArrestProductDAO;
import com.xcs.phase2.model.prove.ProveArrestProduct;
import com.xcs.phase2.request.prove.ProveArrestProductgetByConReq;
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
public class ProveArrestProductController {

    private static final Logger log = LoggerFactory.getLogger(ProveArrestProductController.class);

    @Autowired
    private ProveArrestProductDAO proveArrestProductDAO;

    @PostMapping(value = "/ProveArrestProductgetByCon")
    public ResponseEntity ProveArrestProductgetByCon(@RequestBody ProveArrestProductgetByConReq req) {


        log.info("============= Start API ProveArrestProductgetByCon ================");
        MessageResponse msg = new MessageResponse();
        ProveArrestProduct res = null;
        Boolean checkType = true;
        try {

            res = proveArrestProductDAO.ProveArrestProductgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveArrestProductgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
