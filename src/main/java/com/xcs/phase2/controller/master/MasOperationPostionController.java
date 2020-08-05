package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasOperationPositionDAO;
import com.xcs.phase2.model.master.MasOperationPosition;
import com.xcs.phase2.request.master.MasOperationPositionGetByConReq;
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
public class MasOperationPostionController {

    private static final Logger log = LoggerFactory.getLogger(MasOperationPostionController.class);

    @Autowired
    private MasOperationPositionDAO masOperationPositionDAO;

    @PostMapping(value = "/MasOperationPositionGetByCon")
    public ResponseEntity MasOperationPositionGetByCon(@RequestBody MasOperationPositionGetByConReq req) {

        log.info("============= Start API MasOperationPositionGetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasOperationPosition> res = null;
        Boolean checkType = true;
        try {

            res = masOperationPositionDAO.MasOperationPositionGetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasOperationPositionGetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

}
