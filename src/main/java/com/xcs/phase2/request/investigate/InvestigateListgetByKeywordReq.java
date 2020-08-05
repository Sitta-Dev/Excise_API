package com.xcs.phase2.request.investigate;

import lombok.Data;

@Data
public class InvestigateListgetByKeywordReq extends InvestigateRequest {

    private String TEXT_SEARCH;
    private String STAFF_CODE;
}
