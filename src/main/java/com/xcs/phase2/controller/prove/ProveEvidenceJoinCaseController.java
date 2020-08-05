package com.xcs.phase2.controller.prove;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.prove.ProveEvidenceJoinCaseDAO;
import com.xcs.phase2.model.prove.ProveEvidenceJoinCase;
import com.xcs.phase2.request.prove.ProveEvidenceJoinCasegetByConReq;
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
public class ProveEvidenceJoinCaseController {

    private static final Logger log = LoggerFactory.getLogger(ProveEvidenceJoinCaseController.class);

    @Autowired
    private ProveEvidenceJoinCaseDAO proveEvidenceJoinCaseDAO;

    @PostMapping(value = "/ProveEvidenceJoinCasegetByCon")
    public ResponseEntity ProveEvidenceJoinCasegetByCon(@RequestBody ProveEvidenceJoinCasegetByConReq req) {

        log.info("============= Start API ProveEvidenceJoinCasegetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<ProveEvidenceJoinCase> res = null;
        Boolean checkType = true;
        try {

            res = proveEvidenceJoinCaseDAO.ProveEvidenceJoinCasegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveEvidenceJoinCasegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
