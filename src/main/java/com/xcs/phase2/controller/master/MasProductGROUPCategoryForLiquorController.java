package com.xcs.phase2.controller.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasProductGROUPCategoryForLiquorDAO;
import com.xcs.phase2.model.master.MasProductGROUPCategoryForLiquor;
import com.xcs.phase2.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MasProductGROUPCategoryForLiquorController {

    private static final Logger log = LoggerFactory.getLogger(MasProductSizeController.class);

    @Autowired
    private MasProductGROUPCategoryForLiquorDAO masProductGROUPCategoryForLiquorDAO;

    @PostMapping(value = "/MasProductGROUPCategoryForLiquorgetByCon")
    public ResponseEntity MasProductGROUPCategoryForLiquorgetByCon() {

        log.info("============= Start API MasProductGROUPCategoryForLiquorgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasProductGROUPCategoryForLiquor> res = null;
        Boolean checkType = true;
        try {

            res = masProductGROUPCategoryForLiquorDAO.MasProductGROUPCategoryForLiquorgetByCon();

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasProductGROUPCategoryForLiquorgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
