package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductCategoryGroupDAO;
import com.xcs.phase2.model.master.MasProductCategoryGroup;
import com.xcs.phase2.request.master.MasProductCategoryGroupgetByConReq;
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
public class MasProductCategoryGroupController {

    private static final Logger log = LoggerFactory.getLogger(MasProductSizeController.class);

    @Autowired
    private MasProductCategoryGroupDAO masProductCategoryGroupDAO;

    @PostMapping(value = "/MasProductCategoryGroupgetByCon")
    public ResponseEntity MasProductCategoryGroupgetByCon(@RequestBody MasProductCategoryGroupgetByConReq req) {

        log.info("============= Start API MasProductCategoryGroupgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductCategoryGroup> res = null;
        Boolean checkType = true;
        try {

            res = masProductCategoryGroupDAO.MasProductCategoryGroupgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductCategoryGroupgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
