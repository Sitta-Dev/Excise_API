package com.xcs.phase2.controller.investigate;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.investigate.InvestigateLawsuitResultCountDAO;
import com.xcs.phase2.model.investigate.InvestigateLawsuitResultCount;
import com.xcs.phase2.request.investigate.InvestigateLawsuitResultCountgetByLawbreakerIDReq;
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
public class InvestigateLawsuitResultCountController {

    private static final Logger log = LoggerFactory.getLogger(InvestigateController.class);

    @Autowired
    private InvestigateLawsuitResultCountDAO investigateLawsuitResultCountDAO;

    @PostMapping(value = "/InvestigateLawsuitResultCountgetByLawbreakerID")
    public ResponseEntity InvestigateLawsuitResultCountgetByLawbreakerID(@RequestBody InvestigateLawsuitResultCountgetByLawbreakerIDReq req) {

        log.info("============= Start API InvestigateLawsuitResultCountgetByLawbreakerID ================");
        MessageResponse msg = new MessageResponse();
        InvestigateLawsuitResultCount res = null;
        Boolean checkType = true;
        try {

            res = investigateLawsuitResultCountDAO.InvestigateLawsuitResultCountgetByLawbreakerID(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API InvestigateLawsuitResultCountgetByLawbreakerID =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
