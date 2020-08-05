package com.xcs.phase2.controller.other;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.other.VersionDAO;
import com.xcs.phase2.model.other.Version;
import com.xcs.phase2.request.other.CheckVersionReq;
import com.xcs.phase2.request.other.DownloadNewVersionReq;
import com.xcs.phase2.request.other.UpdateDeleteVersionReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.Other.InsertNewVersionResponse;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class VersionController {

    private static final Logger log = LoggerFactory.getLogger(VersionController.class);

    @Autowired
    private VersionDAO versionDAO;

    @PostMapping(value = "/CheckVersion")
    public ResponseEntity CheckVersion(@RequestBody CheckVersionReq req) {

        log.info("============= Start API CheckVersion ================");
        MessageResponse msg = new MessageResponse();
        List<Version> res = null;
        Boolean checkType = true;
        try {

            res = versionDAO.CheckVersion(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CheckVersion =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @RequestMapping(value = "/DownloadNewVersion.html/{version}", method = RequestMethod.GET)
    public @ResponseBody
    byte[] downloadFileApk(@PathVariable("version") String version , HttpServletResponse response) throws IOException {

        DownloadNewVersionReq req = new DownloadNewVersionReq();
        req.setVERSION_ID(version);

        Version res = versionDAO.DownloadNewVersion(req);

        File file = new File(res.getFILE_PATH());
        response.setContentType("application/octet-stream");
        //response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentLength((int) file.length());
        return FileUtils.readFileToByteArray(file);
    }

    @PostMapping(value = "/InsertNewVersion")
    public ResponseEntity InsertNewVersion(@RequestParam("FILE") MultipartFile FILE,
                                           @RequestParam("VERSION_TYPE") int VERSION_TYPE,
                                           @RequestParam("VERSION_NAME") String VERSION_NAME) {


        Version req = new Version();
        req.setFILE(FILE);
        req.setVERSION_TYPE(VERSION_TYPE);
        req.setVERSION_NAME(VERSION_NAME);

        log.info("============= Start API InsertNewVersion ================");
        MessageResponse msg = new MessageResponse();
        InsertNewVersionResponse res = null;
        Boolean checkType = true;
        try {

            res = versionDAO.InsertNewVersion(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API InsertNewVersion =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/UpdateDeleteVersion")
    public ResponseEntity UpdateDeleteVersion(@RequestBody UpdateDeleteVersionReq req) {

        log.info("============= Start API UpdateDeleteVersion ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(versionDAO.UpdateDeleteVersion(req)){
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
        log.info("============= End API UpdateDeleteVersion =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }



//    @PostMapping(value = "/DownloadNewVersion")
//    public ResponseEntity DownloadNewVersion(@RequestBody DownloadNewVersionReq req) {
//
//
//        log.info("============= Start API DownloadNewVersion ================");
//        MessageResponse msg = new MessageResponse();
//        Version res = null;
//        Boolean checkType = true;
//        try {
//
//            res = versionDAO.DownloadNewVersion(req);
//
//        } catch (Exception e) {
//            checkType = false;
//            msg.setIsSuccess(Message.FALSE);
//            msg.setMsg(e.getMessage());
//
//        }
//        log.info("============= End API DownloadNewVersion =================");
//        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
//    }



//    @PostMapping(value = "/InsertNewVersion")
//    public ResponseEntity InsertNewVersion(@RequestBody Version req) {
//
//        log.info("============= Start API InsertNewVersion ================");
//        InsertNewVersionResponse res = null;
//        try {
//
//            res = versionDAO.InsertNewVersion(req);
//
//        } catch (Exception e) {
//            res.setIsSuccess(Message.FALSE);
//            res.setMsg(e.getMessage());
//        }
//        log.info("============= End API InsertNewVersion =================");
//        return new ResponseEntity(res, HttpStatus.OK);
//    }


}
