package com.xcs.phase2.response.adjust;

import lombok.Data;

import java.util.List;

@Data
public class AdjustCompareDetailinsAllResponse extends AdjustResponse{

    private String IsSuccess;
    private String Msg;
    private int COMPARE_DETAIL_ID;
    private List<AdjustCompareDetailPaymentResponse> AdjustCompareDetailPayment;
    private List<AdjustCompareDetailFineResponse> AdjustCompareDetailFine;
    private List<AdjustComparePaymentResponse> AdjustComparePayment;
}
