package com.xcs.phase2.dao.uac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class UploadFileDAOImpl implements UploadFileDAO {

    private static final Logger log = LoggerFactory.getLogger(UploadFileDAOImpl.class);

    @Value("${folder.document}")
    private String folderDocument;

    @Value("${folder.person}")
    private String folderPerson;

    @Value("${folder.product}")
    private String folderProduct;

    @Override
    public String updoadFile(String fileName, String docType, String typeFile, String base64) {
        return conventBase64ToFile(fileName, docType, typeFile, base64);
    }

    private String conventBase64ToFile(String fileName, String docType, String typeFile, String base64) {

        String pathFile = "";

//        String folderDocument = "D:/XCS60/Document/";
//        String folderPerson = "D:/XCS60/Person/";
//        String folderProduct = "D:/XCS60/Product/";


        if ("Document".equals(docType)) {
            pathFile = folderDocument + fileName + "." + typeFile;
        } else if ("Person".equals(docType)) {
            pathFile = folderPerson + fileName + "." + typeFile;
        } else if ("Product".equals(docType)) {
            pathFile = folderProduct + fileName + "." + typeFile;
        }

        log.info(" pathFile : " + pathFile);


        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = decoder.decodeBuffer(base64);
            if ("pdf".equals(typeFile) || "docx".equals(typeFile) || "xlsx".equals(typeFile)) {
                writeByteArraysToFile(pathFile, decodedBytes);
            } else {
                writeByteArraysToImg(pathFile, decodedBytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathFile;
    }

    private void writeByteArraysToFile(String fileName, byte[] content) throws IOException {

        File file = new File(fileName);
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        writer.write(content);
        writer.flush();
        writer.close();

    }

    private void writeByteArraysToImg(String fileName, byte[] content) throws IOException {

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(content));

        File path = new File(fileName);
        ImageIO.write(image, "jpg", path);

    }
}
