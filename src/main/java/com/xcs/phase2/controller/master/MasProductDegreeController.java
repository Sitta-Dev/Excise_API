package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductDegreeDAO;
import com.xcs.phase2.model.master.MasProductDegree;
import com.xcs.phase2.request.master.MasProductDegreegetByConReq;
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
public class MasProductDegreeController {

    private static final Logger log = LoggerFactory.getLogger(MasProductDegreeController.class);

    @Autowired
    private MasProductDegreeDAO masProductDegreeDAO;

    @PostMapping(value = "/MasProductDegreegetByCon")
    public ResponseEntity MasProductDegreegetByCon(@RequestBody MasProductDegreegetByConReq req) {

        log.info("============= Start API MasProductDegreegetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductDegree> res = null;
        Boolean checkType = true;
        try {

            res = masProductDegreeDAO.MasProductDegreegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductDegreegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

}
