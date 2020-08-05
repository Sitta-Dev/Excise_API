package com.xcs.phase2.model.document;

import com.xcs.phase2.request.Request;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Document extends Request {

    private int DOCUMENT_ID;
    private String REFERENCE_CODE;
    private String FILE_PATH;
    private String DATA_SOURCE;
    private int DOCUMENT_TYPE;
    private String DOCUMENT_NAME;
    private String IS_ACTIVE;
    private String DOCUMENT_OLD_NAME;

    private MultipartFile FILE;
    private String FOLDER;

}
