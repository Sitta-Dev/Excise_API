package com.xcs.phase2.dao.document;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.UacExt;
import com.xcs.phase2.model.document.Document;
import com.xcs.phase2.request.document.DocumentupdDeleteReq;
import com.xcs.phase2.request.document.GetDocumentByConReq;
import com.xcs.phase2.response.document.DocumentinsAllResponse;
import com.xcs.phase2.storage.StorageException;
import com.xcs.phase2.storage.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class DocumentDAOImpl extends UacExt implements DocumentDAO {

    private static final Logger log = LoggerFactory.getLogger(DocumentDAOImpl.class);

    @Value("${folder.document}")
    private String folderDocument;

    @Value("${folder.person}")
    private String folderPerson;

    @Value("${folder.product}")
    private String folderProduct;

    @Value("${folder.other}")
    private String folderOther;

    private final Path rootLocationVdo;
    private final Path rootLocationDocument;
    private final Path rootLocationPerson;
    private final Path rootLocationProduct;
    private final Path rootLocationOther;

    @Autowired
    public DocumentDAOImpl(StorageProperties properties) {
        this.rootLocationVdo = Paths.get(properties.getUploadVdo()).toAbsolutePath().normalize();
        this.rootLocationDocument = Paths.get(properties.getUploadDocument()).toAbsolutePath().normalize();
        this.rootLocationPerson = Paths.get(properties.getUploadPerson()).toAbsolutePath().normalize();
        this.rootLocationProduct = Paths.get(properties.getUploadProduct()).toAbsolutePath().normalize();
        this.rootLocationOther = Paths.get(properties.getUploadOther()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.rootLocationVdo);
            Files.createDirectories(this.rootLocationDocument);
            Files.createDirectories(this.rootLocationPerson);
            Files.createDirectories(this.rootLocationProduct);
            Files.createDirectories(this.rootLocationOther);
        } catch (Exception ex) {
            throw new StorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public List<Document> GetImagePerson() {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("  select " +
                        "  OPS_DOCUMENT.DOCUMENT_ID," +
                        "  OPS_DOCUMENT.REFERENCE_CODE," +
                        "  OPS_DOCUMENT.FILE_PATH," +
                        "  OPS_DOCUMENT.IS_ACTIVE" +
                        "  from OPS_DOCUMENT" +
                        "  inner join MAS_PERSON on(OPS_DOCUMENT.REFERENCE_CODE = MAS_PERSON.PERSON_ID)" +
                        "  where MAS_PERSON.MISTREAT_NO > 0 and OPS_DOCUMENT.FILE_PATH like '%XCS60\\Person%'  and OPS_DOCUMENT.IS_ACTIVE = 1 ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<Document> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
                Document item = new Document();
                item.setDOCUMENT_ID(rs.getInt("DOCUMENT_ID"));
                item.setREFERENCE_CODE(rs.getString("REFERENCE_CODE"));
                item.setFILE_PATH(rs.getString("FILE_PATH"));
                item.setIS_ACTIVE(rs.getString("IS_ACTIVE"));

                return item;
            }
        });

        return dataList;

    }

    @Override
    public List<Document> GetDocumentByCon(GetDocumentByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("select " +
                        "DOCUMENT_ID," +
                        "REFERENCE_CODE," +
                        "FILE_PATH," +
                        "DATA_SOURCE," +
                        "DOCUMENT_TYPE," +
                        "DOCUMENT_NAME," +
                        "DOCUMENT_OLD_NAME," +
                        "IS_ACTIVE" +
                        " from OPS_DOCUMENT  where  ");
        sqlBuilder.append(" REFERENCE_CODE = '"+req.getREFERENCE_CODE()+"' and DOCUMENT_TYPE = '"+req.getDOCUMENT_TYPE()+"' ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<Document> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
                Document item = new Document();
                item.setDOCUMENT_ID(rs.getInt("DOCUMENT_ID"));
                item.setREFERENCE_CODE(rs.getString("REFERENCE_CODE"));
                item.setFILE_PATH(rs.getString("FILE_PATH"));
                item.setDATA_SOURCE(rs.getString("DATA_SOURCE"));
                item.setDOCUMENT_TYPE(rs.getInt("DOCUMENT_TYPE"));
                item.setDOCUMENT_NAME(rs.getString("DOCUMENT_NAME"));
                item.setIS_ACTIVE(rs.getString("IS_ACTIVE"));
                item.setDOCUMENT_OLD_NAME(rs.getString("DOCUMENT_OLD_NAME"));

                return item;
            }
        });

        return dataList;

    }

    @Override
    public DocumentinsAllResponse DocumentinsAll(Document req) {

        DocumentinsAllResponse res = new DocumentinsAllResponse();
        Path targetLocation = null;
        // Normalize file name
        String fileName = StringUtils.cleanPath(req.getFILE().getOriginalFilename());
        try
        {
            if (req.getFILE().isEmpty())
            {
                throw new StorageException("Failed to store empty file " + req.getFILE().getOriginalFilename());
            }
            // Copy file to the target location (Replacing existing file with the same name)

            if ("document".equals(req.getFOLDER())) {
                targetLocation = this.rootLocationDocument.resolve(fileName);
            } else if ("person".equals(req.getFOLDER())) {
                targetLocation = this.rootLocationPerson.resolve(fileName);
            } else if ("product".equals(req.getFOLDER())) {
                targetLocation = this.rootLocationProduct.resolve(fileName);
            } else if ("vdo".equals(req.getFOLDER())) {
                targetLocation = this.rootLocationVdo.resolve(fileName);
            }else{
                targetLocation = this.rootLocationOther.resolve(fileName);
            }
            Files.copy(req.getFILE().getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        }
        catch (IOException e)
        {
            throw new StorageException("Failed to store file " + req.getFILE().getOriginalFilename(), e);
        }

        try {


            String DOCUMENT_ID = getSequences("SELECT OPS_DOCUMENT_SEQ.NEXTVAL FROM DUAL");
            StringBuilder sqlBuilder = new StringBuilder()
                    .append("Insert into OPS_DOCUMENT ( " +
                            "DOCUMENT_ID," +
                            "REFERENCE_CODE," +
                            "FILE_PATH," +
                            "DATA_SOURCE," +
                            "DOCUMENT_TYPE," +
                            "DOCUMENT_NAME," +
                            "DOCUMENT_OLD_NAME," +
                            "IS_ACTIVE" +
                            " ) values (" +
                            "'" + DOCUMENT_ID + "'," +
                            "'" + req.getREFERENCE_CODE() + "'," +
                            "'" + targetLocation + "'," +
                            "'" + req.getDATA_SOURCE() + "'," +
                            "'" + req.getDOCUMENT_TYPE() + "'," +
                            "'" + req.getDOCUMENT_NAME() + "'," +
                            "'" + req.getDOCUMENT_OLD_NAME() + "'," +
                            "'" + 1 + "'" +
                            " )");

            log.info("[SQL] : " + sqlBuilder.toString());
            getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
            res.setDOCUMENT_ID(Integer.parseInt(DOCUMENT_ID));

            res.setIsSuccess(Message.TRUE);
            res.setMsg(Message.COMPLETE);

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
            res.setDOCUMENT_ID(0);
            return res;
        }
    }

    @Override
    public Boolean DocumentupdDelete(DocumentupdDeleteReq req) {

        String filePath = getPathFile("SELECT FILE_PATH FROM OPS_DOCUMENT WHERE DOCUMENT_ID = '" + req.getDOCUMENT_ID() + "' ");


        StringBuilder sqlBuilder1 = new StringBuilder().append("UPDATE OPS_DOCUMENT SET IS_ACTIVE = '0' WHERE DOCUMENT_ID = '" + req.getDOCUMENT_ID() + "' ");

        getJdbcTemplate().update(sqlBuilder1.toString(), new Object[]{});

        deleteFile(filePath);

        return true;
    }

    @Override
    public String getPath(String documentId) {

        String filePath = getPathFile("SELECT FILE_PATH FROM OPS_DOCUMENT WHERE DOCUMENT_ID = '" + documentId + "' ");

        return filePath;
    }

    private void conventBase64ToFile(String pathFile, String typeFile, String content) {


        log.info(" pathFile : " + pathFile);


        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = decoder.decodeBuffer(content);
            if ("pdf".equals(typeFile) || "docx".equals(typeFile) || "xlsx".equals(typeFile)) {
                writeByteArraysToFile(pathFile, decodedBytes);
            } else {
                writeByteArraysToImg(pathFile, decodedBytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void deleteFile(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        } catch (IOException e) {
            System.out.println("Invalid permissions.");
        }

        System.out.println("Deletion successful.");
    }
}
