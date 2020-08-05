package com.xcs.phase2.request.provestorage;

import com.xcs.phase2.request.compare.CompareRequest;
import lombok.Data;

@Data
public class ProvestorageListgetByConAdvReq extends CompareRequest {

    private String ARREST_CODE;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String DELIVERY_NO;
    private String DELIVERY_DATE_START;
    private String DELIVERY_DATE_END;
    private String DELIVERY_NAME;
    private String DELIVERY_OFFICE_NAME;
    private String EVIDENCE_IN_CODE;
    private String EVIDENCE_IN_DATE_START;
    private String EVIDENCE_IN_DATE_END;
    private String RECEIVE_OFFICE_NAME;
    private String PROVE_NAME;
    private String IS_RECEIVE;
    private String ACCOUNT_OFFICE_CODE;






}
