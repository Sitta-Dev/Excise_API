package com.xcs.phase2.request.evidencein;

import lombok.Data;

@Data
public class EvidenceInBookListgetByConAdvReq extends EvidenceInRequest {

    private String EVIDENCE_OUT_NO;
    private String EVIDENCE_OUT_CODE;
    private String EVIDENCE_OUT_STAFF;
    private String OPERATION_OFFICE_NAME;
    private String RETURN_DATE_FROM;
    private String RETURN_DATE_TO;
    private String ACCOUNT_OFFICE_CODE;
}
