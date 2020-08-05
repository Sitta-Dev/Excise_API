package com.xcs.phase2.response.compare;

import lombok.Data;

import java.util.List;

@Data
public class ComparePaymentResponse extends CompareResponse{

    private int PAYMENT_ID;
    private List<ComparePaymentDetailResponse> ComparePaymentDetail;
}
