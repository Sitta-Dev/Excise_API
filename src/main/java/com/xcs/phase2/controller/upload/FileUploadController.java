package com.xcs.phase2.controller.upload;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.uac.UploadFileResponse;
import com.xcs.phase2.storage.FileSystemStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileSystemStorageService storageService;

    @PostMapping(value = "/FileUpload")
    public ResponseEntity FileUpload(@RequestParam("file") MultipartFile file,@RequestParam("type") String type) {


        log.info("============= Start API FileUpload ================");
        MessageResponse msg = new MessageResponse();
        UploadFileResponse res = new UploadFileResponse();
        Boolean checkType = true;
        String path = "";
        try {

            path = storageService.store(file,type);

            res.setPath(path);
        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API FileUpload =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }


}
