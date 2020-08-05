package com.xcs.phase2.request.evidencein;

import lombok.Data;

@Data
public class EvidenceInBookListgetByKeywordReq extends EvidenceInRequest {

    private String TEXT_SEARCH;
    private String ACCOUNT_OFFICE_CODE;
}
