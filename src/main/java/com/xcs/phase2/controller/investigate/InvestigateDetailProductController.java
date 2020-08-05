package com.xcs.phase2.controller.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.investigate.InvestigateDetailProductDAO;
import com.xcs.phase2.model.investigate.InvestigateDetailProduct;
import com.xcs.phase2.request.investigate.InvestigateDetailProductupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.investigate.InvestigateDetailProductinsAllResponse;
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
public class InvestigateDetailProductController {

    private static final Logger log = LoggerFactory.getLogger(InvestigateDetailProductController.class);

    @Autowired
    private InvestigateDetailProductDAO investigateDetailProductDAO;



    @PostMapping(value = "/InvestigateDetailProductinsAll")
    public ResponseEntity InvestigateDetailProductinsAll(@RequestBody List<InvestigateDetailProduct> req) {

        log.info("============= Start API InvestigateDetailProductinsAll ================");
        InvestigateDetailProductinsAllResponse res = null;
        try {

            res = investigateDetailProductDAO.InvestigateDetailProductinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API InvestigateDetailProductinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailProductupdByCon")
    public ResponseEntity InvestigateDetailProductupdByCon(@RequestBody List<InvestigateDetailProduct> req) {

        log.info("============= Start API InvestigateDetailProductupdByCon ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(investigateDetailProductDAO.InvestigateDetailProductupdByCon(req)){
                msg.setIsSuccess(Message.TRUE);
                msg.setMsg(Message.COMPLETE);
            }else{
                msg.setIsSuccess(Message.FALSE);
                msg.setMsg(Message.NOT_COMPLETE);
            }

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API InvestigateDetailProductupdByCon =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping(value = "/InvestigateDetailProductupdDelete")
    public ResponseEntity InvestigateDetailProductupdDelete(@RequestBody List<InvestigateDetailProductupdDeleteReq> req) {

        log.info("============= Start API InvestigateDetailStaffupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(investigateDetailProductDAO.InvestigateDetailProductupdDelete(req)){
                msg.setIsSuccess(Message.TRUE);
                msg.setMsg(Message.COMPLETE);
            }else{
                msg.setIsSuccess(Message.FALSE);
                msg.setMsg(Message.NOT_COMPLETE);
            }

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API InvestigateDetailProductupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
