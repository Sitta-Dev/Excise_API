package com.xcs.phase2.controller.uac;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.ModuleDAO;
import com.xcs.phase2.model.uac.Module;
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
public class ModuleController {

    private static final Logger log = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private ModuleDAO moduleDAO;

    @PostMapping(value = "/ModulegetAll")
    public ResponseEntity ModulegetAll() {

        log.info("============= Start API ModulegetAll ================");
        MessageResponse msg = new MessageResponse();
        List<Module> res = null;
        Boolean checkType = true;
        try {

            res = moduleDAO.ModulegetAll();

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ModulegetAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
