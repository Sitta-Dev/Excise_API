package com.xcs.phase2.controller.prove;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveArrestIndictmentProductWebDAO;
import com.xcs.phase2.model.prove.ProveArrestIndictmentProductWeb;
import com.xcs.phase2.request.prove.ProveArrestIndictmentProductWebgetByConReq;
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
public class ProveArrestIndictmentProductWebController {

    private static final Logger log = LoggerFactory.getLogger(ProveArrestIndictmentProductWebController.class);

    @Autowired
    private ProveArrestIndictmentProductWebDAO proveArrestIndictmentProductWebDAO;

    @PostMapping(value = "/ProveArrestIndictmentProductWebgetByCon")
    public ResponseEntity ProveArrestIndictmentProductWebgetByCon(@RequestBody ProveArrestIndictmentProductWebgetByConReq req) {

        log.info("============= Start API ProveArrestIndictmentProductWebgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<ProveArrestIndictmentProductWeb> res = null;
        Boolean checkType = true;
        try {

            res = proveArrestIndictmentProductWebDAO.ProveArrestIndictmentProductWebgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveArrestIndictmentProductWebgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
