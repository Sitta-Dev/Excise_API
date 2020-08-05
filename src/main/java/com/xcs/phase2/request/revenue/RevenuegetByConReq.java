package com.xcs.phase2.request.revenue;

import lombok.Data;

@Data
public class RevenuegetByConReq extends RevenueRequest {

    private int REVENUE_ID;
    private String OFFICE_CODE;
}
