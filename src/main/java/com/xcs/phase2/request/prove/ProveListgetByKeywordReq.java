package com.xcs.phase2.request.prove;

import lombok.Data;

@Data
public class ProveListgetByKeywordReq extends ProveRequest {

    private String TEXT_SEARCH;
    private String ACCOUNT_OFFICE_CODE;
}
