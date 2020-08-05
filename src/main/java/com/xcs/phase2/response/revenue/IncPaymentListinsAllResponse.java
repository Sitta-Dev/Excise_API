package com.xcs.phase2.response.revenue;

import lombok.Data;

import java.util.List;

@Data
public class IncPaymentListinsAllResponse extends RevenueResponse {

    private String IsSuccess;
    private String Msg;
    private List<RevenueIncPaymentResponse> RevenueIncPayment;
}
