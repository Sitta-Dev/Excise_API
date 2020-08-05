package com.xcs.phase2.request.compare;

import lombok.Data;

@Data
public class CompareListgetByKeywordReq extends CompareRequest {

    private String TEXT_SEARCH;
    private String ACCOUNT_OFFICE_CODE;

}
