package com.xcs.phase2.controller.document;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.document.PersonRelationshipDAO;
import com.xcs.phase2.model.document.PersonRelationship;
import com.xcs.phase2.request.document.PersonRelationshipgetByPersonIdReq;
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
public class PersonRelationshipController {

    private static final Logger log = LoggerFactory.getLogger(PersonRelationshipController.class);

    @Autowired
    private PersonRelationshipDAO personRelationshipDAO;

    @PostMapping(value = "/PersonRelationshipgetByPersonId")
    public ResponseEntity PersonRelationshipgetByPersonId(@RequestBody PersonRelationshipgetByPersonIdReq req) {

        log.info("============= Start API PersonRelationshipgetByPersonId ================");
        MessageResponse msg = new MessageResponse();
        List<PersonRelationship> res = null;
        Boolean checkType = true;
        try {

            res = personRelationshipDAO.PersonRelationshipgetByPersonId(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API PersonRelationshipgetByPersonId =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

}
