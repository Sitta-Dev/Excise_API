package com.xcs.phase2.request.document;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class GetDocumentByConReq extends Request {

    private int DOCUMENT_TYPE;
    private String REFERENCE_CODE;
}
