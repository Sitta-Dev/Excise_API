package com.xcs.phase2.request.revenue;

import lombok.Data;

@Data
public class RevenueCompareStatusReq extends RevenueRequest {

    private String COMPARE_DETAIL_ID;
    private String IS_REVENUE;
}
