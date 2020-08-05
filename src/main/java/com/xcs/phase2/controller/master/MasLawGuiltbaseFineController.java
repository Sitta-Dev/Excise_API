package com.xcs.phase2.controller.master;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.master.MasLawGuiltbaseFineDAO;
import com.xcs.phase2.model.master.MasLawGuiltbaseFine;
import com.xcs.phase2.request.master.MasLawGuiltbaseFinegetByConReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasLawGuiltbaseFineinsAllResponse;
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
public class MasLawGuiltbaseFineController {

    private static final Logger log = LoggerFactory.getLogger(MasLawGuiltbaseFineController.class);

    @Autowired
    private MasLawGuiltbaseFineDAO masLawGuiltbaseFineDAO;


    @PostMapping(value = "/MasLawGuiltbaseFinegetByCon")
    public ResponseEntity MasLawGuiltbaseFinegetByCon(@RequestBody MasLawGuiltbaseFinegetByConReq req) {

        log.info("============= Start API MasLawGuiltbaseFinegetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<MasLawGuiltbaseFine> res = null;
        Boolean checkType = true;
        try {

            res = masLawGuiltbaseFineDAO.MasLawGuiltbaseFinegetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API MasLawGuiltbaseFinegetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGuiltbaseFineinsAll")
    public ResponseEntity MasLawGuiltbaseFineinsAll(@RequestBody MasLawGuiltbaseFine req) {

        log.info("============= Start API MasLawGuiltbaseFineinsAll ================");
        MasLawGuiltbaseFineinsAllResponse res = null;
        try {

            res = masLawGuiltbaseFineDAO.MasLawGuiltbaseFineinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API MasLawGuiltbaseFineinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/MasLawGuiltbaseFineupdAll")
    public ResponseEntity MasLawGuiltbaseFineupdAll(@RequestBody MasLawGuiltbaseFine req) {

        log.info("============= Start API MasLawGuiltbaseFineupdAll ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(masLawGuiltbaseFineDAO.MasLawGuiltbaseFineupdAll(req)){
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
        log.info("============= End API MasLawGuiltbaseFineupdAll =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }


}
