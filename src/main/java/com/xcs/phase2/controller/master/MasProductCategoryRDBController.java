package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductCategoryRDBDAO;
import com.xcs.phase2.model.master.MasProductCategoryRDB;
import com.xcs.phase2.request.master.MasProductCategoryRDBgetByConReq;
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
public class MasProductCategoryRDBController {

    private static final Logger log = LoggerFactory.getLogger(MasProductSizeController.class);

    @Autowired
    private MasProductCategoryRDBDAO masProductCategoryRDBDAO;

    @PostMapping(value = "/MasProductCategoryRDBgetByCon")
    public ResponseEntity MasProductCategoryRDBgetByCon(@RequestBody MasProductCategoryRDBgetByConReq req) {

        log.info("============= Start API MasProductCategoryRDBgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductCategoryRDB> res = null;
        Boolean checkType = true;
        try {

            res = masProductCategoryRDBDAO.MasProductCategoryRDBgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductCategoryRDBgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
