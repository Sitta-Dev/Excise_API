package com.xcs.phase2.controller.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.revenue.RevenueReturnREFnoDAO;
import com.xcs.phase2.model.revenue.RevenueReturnREFnoModel;
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
public class RevenueReturnREFnoController {

    private static final Logger log = LoggerFactory.getLogger(RevenueReturnREFnoController.class);

    @Autowired
    private RevenueReturnREFnoDAO revenueReturnREFnoDAO;

    @PostMapping(value = "/RevenueReturnUpdateREFno")
    public ResponseEntity RevenueReturnUpdateREFno(@RequestBody List<RevenueReturnREFnoModel> req) {

        log.info("============= Start API RevenueReturnUpdateREFno ================");
        MessageResponse msg = new MessageResponse();
        List<RevenueReturnREFnoModel> res = null;
        Boolean checkType = true;
        try {

            res = revenueReturnREFnoDAO.RevenueReturnUpdateREFno(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenueReturnUpdateREFno =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }


}
