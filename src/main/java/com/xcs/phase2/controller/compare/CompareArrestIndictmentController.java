package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.CompareArrestIndictmentDAO;
import com.xcs.phase2.model.compare.CompareArrestgetByIndictment;
import com.xcs.phase2.request.compare.CompareArrestgetByIndictmentIDReq;
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
public class CompareArrestIndictmentController {

    private static final Logger log = LoggerFactory.getLogger(CompareArrestIndictmentController.class);

    @Autowired
    private CompareArrestIndictmentDAO compareArrestIndictmentDAO;

    @PostMapping(value = "/CompareArrestgetByIndictmentID")
    public ResponseEntity CompareArrestgetByIndictmentID(@RequestBody CompareArrestgetByIndictmentIDReq req) {


        log.info("============= Start API CompareArrestgetByIndictmentID ================");
        MessageResponse msg = new MessageResponse();
        CompareArrestgetByIndictment res = null;
        Boolean checkType = true;
        try {

            res = compareArrestIndictmentDAO.CompareArrestgetByIndictment(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CompareArrestgetByIndictmentID =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
