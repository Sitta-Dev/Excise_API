package com.xcs.phase2.request.revenue;

import lombok.Data;

@Data
public class RevenuePaymentupdByConReq extends RevenueRequest {

    private String PAYMENT_ID;
    private String IS_REVENUE;
}
