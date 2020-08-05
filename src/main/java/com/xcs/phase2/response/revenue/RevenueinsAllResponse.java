package com.xcs.phase2.response.revenue;

import lombok.Data;

import java.util.List;

@Data
public class RevenueinsAllResponse extends RevenueResponse {

    private String IsSuccess;
    private String Msg;
    private int REVENUE_ID;
    private List<RevenueDetailResponse> RevenueDetail;
    private List<RevenueStaffResponse> RevenueStaff ;
}
