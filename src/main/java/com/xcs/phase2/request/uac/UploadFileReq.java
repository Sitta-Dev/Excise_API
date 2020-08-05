package com.xcs.phase2.request.uac;

import lombok.Data;

@Data
public class UploadFileReq extends UacRequest {

    private String FILE_NAME;
    private String CONTENT;
    private String FILE_TYPE;
    private String DOC_TYPE;
}
