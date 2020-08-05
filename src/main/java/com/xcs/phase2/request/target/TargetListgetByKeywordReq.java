package com.xcs.phase2.request.target;

import lombok.Data;

@Data
public class TargetListgetByKeywordReq extends TargetRequest {

    private String TEXT_SEARCH;
    private String OFFICE_CODE;
}
