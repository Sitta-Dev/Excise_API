package com.xcs.phase2.response.document;

import com.xcs.phase2.response.Response;
import lombok.Data;

@Data
public class DocumentinsAllResponse extends Response {

    private String IsSuccess;
    private String Msg;
    private int DOCUMENT_ID;
}
