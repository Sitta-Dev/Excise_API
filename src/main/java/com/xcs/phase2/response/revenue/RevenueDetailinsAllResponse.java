package com.xcs.phase2.response.revenue;

import lombok.Data;

@Data
public class RevenueDetailinsAllResponse extends RevenueResponse {

    private String IsSuccess;
    private String Msg;
    private int REVENUE_DETAIL_ID;
}
