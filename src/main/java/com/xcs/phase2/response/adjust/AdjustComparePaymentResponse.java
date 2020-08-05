package com.xcs.phase2.response.adjust;

import lombok.Data;

import java.util.List;

@Data
public class AdjustComparePaymentResponse extends AdjustResponse{

    private int PAYMENT_ID;
    private List<AdjustComparePaymentDetailResponse> AdjustComparePaymentDetail;
}
