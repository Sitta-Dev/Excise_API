package com.xcs.phase2.controller.upload;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.UploadFileDAO;
import com.xcs.phase2.request.uac.UploadFileReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.uac.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadFileController {

    private static final Logger log = LoggerFactory.getLogger(UploadFileController.class);

    @Autowired
    private UploadFileDAO uploadFileDAO;

    @PostMapping(value = "/UploadFile")
    public ResponseEntity UploadFile(@RequestBody UploadFileReq req) {


        log.info("============= Start API UploadFile ================");
        MessageResponse msg = new MessageResponse();
        UploadFileResponse res = new UploadFileResponse();
        Boolean checkType = true;
        String path = "";
        try {

            path = uploadFileDAO.updoadFile(req.getFILE_NAME(),req.getDOC_TYPE(),req.getFILE_TYPE(),req.getCONTENT());

            res.setPath(path);
        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API UploadFile =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
