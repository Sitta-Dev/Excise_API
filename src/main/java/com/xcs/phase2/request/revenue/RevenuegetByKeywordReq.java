package com.xcs.phase2.request.revenue;

import lombok.Data;

@Data
public class RevenuegetByKeywordReq extends RevenueRequest {

    private String TEXT_SEARCH;
    private String ACCOUNT_OFFICE_CODE;

}
