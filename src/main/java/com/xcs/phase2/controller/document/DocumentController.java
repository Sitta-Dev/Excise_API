package com.xcs.phase2.controller.document;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.document.DocumentDAO;
import com.xcs.phase2.model.document.Document;
import com.xcs.phase2.request.document.DocumentupdDeleteReq;
import com.xcs.phase2.request.document.GetDocumentByConReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.document.DocumentinsAllResponse;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class DocumentController {

    private static final Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Value("${apk}")
    private String pathApk;

    @Autowired
    private DocumentDAO documentDAO;

    @PostMapping(value = "/GetImagePerson")
    public ResponseEntity GetImagePerson() {

        log.info("============= Start API GetImagePerson ================");
        MessageResponse msg = new MessageResponse();
        List<Document> res = null;
        Boolean checkType = true;
        try {

            res = documentDAO.GetImagePerson();

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API GetImagePerson =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/GetDocumentByCon")
    public ResponseEntity GetDocumentByCon(@RequestBody GetDocumentByConReq req) {

        log.info("============= Start API GetDocumentByCon ================");
        MessageResponse msg = new MessageResponse();
        List<Document> res = null;
        Boolean checkType = true;
        try {

            res = documentDAO.GetDocumentByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API GetDocumentByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }



    @PostMapping(value = "/DocumentinsAll")
    public ResponseEntity DocumentinsAll(@RequestParam("FILE") MultipartFile FILE,
                                         @RequestParam("DOCUMENT_NAME") String DOCUMENT_NAME,
                                         @RequestParam("DOCUMENT_OLD_NAME") String DOCUMENT_OLD_NAME,
                                         @RequestParam("DOCUMENT_TYPE") int DOCUMENT_TYPE,
                                         @RequestParam("FOLDER") String FOLDER,
                                         @RequestParam("REFERENCE_CODE") String REFERENCE_CODE) {


        Document req = new Document();
        req.setFILE(FILE);
        req.setDOCUMENT_NAME(DOCUMENT_NAME);
        req.setDOCUMENT_OLD_NAME(DOCUMENT_OLD_NAME);
        req.setDOCUMENT_TYPE(DOCUMENT_TYPE);
        req.setFOLDER(FOLDER);
        req.setREFERENCE_CODE(REFERENCE_CODE);

        log.info("============= Start API DocumentinsAll ================");
        MessageResponse msg = new MessageResponse();
        DocumentinsAllResponse res = null;
        Boolean checkType = true;
        try {

            res = documentDAO.DocumentinsAll(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API DocumentinsAll =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/DocumentupdDelete")
    public ResponseEntity DocumentupdDelete(@RequestBody DocumentupdDeleteReq req) {

        log.info("============= Start API DocumentupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if (documentDAO.DocumentupdDelete(req)) {
                msg.setIsSuccess(Message.TRUE);
                msg.setMsg(Message.COMPLETE);
            } else {
                msg.setIsSuccess(Message.FALSE);
                msg.setMsg(Message.NOT_COMPLETE);
            }

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API DocumentupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @RequestMapping(value = "/downloadFile.html/{documentId}", method = RequestMethod.GET)
    public @ResponseBody
    byte[] downloadFile(@PathVariable("documentId") String documentId, HttpServletResponse response) throws IOException {

        String path = documentDAO.getPath(documentId);
        File file = new File(path);
        response.setContentType("application/octet-stream");
        //response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentLength((int) file.length());
        return FileUtils.readFileToByteArray(file);
    }

    @RequestMapping(value = "/getImage.html/{documentId}", method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("documentId") String documentId) throws IOException {

        String path = documentDAO.getPath(documentId);
        File file = new File(path);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(FileUtils.readFileToByteArray(file));
    }

    @RequestMapping(value = "/downloadFileApk.html", method = RequestMethod.GET)
    public @ResponseBody
    byte[] downloadFileApk(HttpServletResponse response) throws IOException {

        File file = new File(pathApk);
        response.setContentType("application/octet-stream");
        //response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentLength((int) file.length());
        return FileUtils.readFileToByteArray(file);
    }
}
