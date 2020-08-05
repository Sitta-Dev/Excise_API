package com.xcs.phase2.controller.compare;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.compare.CompareCountMistreatDAO;
import com.xcs.phase2.model.compare.CompareCountMistreat;
import com.xcs.phase2.request.compare.CompareCountMistreatgetByConReq;
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
public class CompareCountMistreatController {

    private static final Logger log = LoggerFactory.getLogger(CompareController.class);

    @Autowired
    private CompareCountMistreatDAO compareCountMistreatDAO;

    @PostMapping(value = "/CompareCountMistreatgetByCon")
    public ResponseEntity CompareCountMistreatgetByCon(@RequestBody CompareCountMistreatgetByConReq req) {


        log.info("============= Start API CompareCountMistreatgetByCon ================");
        MessageResponse msg = new MessageResponse();
        CompareCountMistreat res = null;
        Boolean checkType = true;
        try {

            res = compareCountMistreatDAO.CompareCountMistreatgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CompareCountMistreatgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
