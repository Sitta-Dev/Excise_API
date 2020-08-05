package com.xcs.phase2.response.adjust;

import lombok.Data;

import java.util.List;

@Data
public class AdjustComparePaymentinsAllResponse extends AdjustResponse{

    private String IsSuccess;
    private String Msg;
    private List<AdjustComparePaymentResponse> ComparePayment;
}
