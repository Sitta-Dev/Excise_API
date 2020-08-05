package com.xcs.phase2.request.evidencein;

import lombok.Data;

@Data
public class EvidenceInListgetByConAdvReq extends EvidenceInRequest {

    private String EVIDENCE_IN_CODE;
    private String EVIDENCE_IN_DATE_START;
    private String EVIDENCE_IN_DATE_TO;
    private String RECEIVER_NAME;
    private String DELIVER_NAME;
    private String RECEIVER_OFFICE_NAME;
    private String DELIVER_OFFICE_NAME;
    private Integer EVIDENCE_IN_TYPE;
    private String DELIVERY_NO;
    private String DELIVERY_DATE_START;
    private String DELIVERY_DATE_TO;
    private Integer IS_RECEIVE;
    private String PROVE_NO;
    private String WAREHOUSE_NAME;
    private String ACCOUNT_OFFICE_CODE;
    private String PROVE_DATE_START;
    private String PROVE_DATE_TO;
    private String PROVE_NO_YEAR;
    private String PROVE_STAFF;

    private String LAWSUIT_DATE_START;
    private String LAWSUIT_DATE_TO;

    private String LAWSUIT_NO;
    private String LAWSUIT_STAFF;

}
