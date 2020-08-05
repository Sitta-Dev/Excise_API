package com.xcs.phase2.controller.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.revenue.RevenueSearchStatus1DAO;
import com.xcs.phase2.model.revenue.RevenueSearchStatus1;
import com.xcs.phase2.request.revenue.RevenueSearchStatus1Req;
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
public class RevenueSearchStatus1Controller {

    private static final Logger log = LoggerFactory.getLogger(RevenueSearchStatus1Controller.class);

    @Autowired
    private RevenueSearchStatus1DAO revenueSearchStatus1DAO;

    @PostMapping(value = "/RevenueSearchStatus1")
    public ResponseEntity RevenueSearchStatus1(@RequestBody RevenueSearchStatus1Req req) {

        log.info("============= Start API RevenueSearchStatus1 ================");
        MessageResponse msg = new MessageResponse();
        List<RevenueSearchStatus1> res = null;
        Boolean checkType = true;
        try {

            res = revenueSearchStatus1DAO.RevenueSearchStatus1(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API RevenueSearchStatus1 =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
