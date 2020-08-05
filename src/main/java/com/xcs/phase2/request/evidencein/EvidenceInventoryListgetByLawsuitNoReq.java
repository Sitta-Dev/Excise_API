package com.xcs.phase2.request.evidencein;

import lombok.Data;

@Data
public class EvidenceInventoryListgetByLawsuitNoReq extends EvidenceInRequest{

    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String IS_OUTSIDE;
    private String OFFICE_CODE;
    private String LAWSUIT_DATE_START;
    private String LAWSUIT_DATE_END;
    private String DELIVERY_DOC_NO_1;

    private String EVIDENCE_IN_DATE_START;
    private String EVIDENCE_IN_DATE_END;
    private String EVIDENCE_IN_ITEM_CODE;


}
