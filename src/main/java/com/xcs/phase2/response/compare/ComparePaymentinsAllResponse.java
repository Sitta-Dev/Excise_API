package com.xcs.phase2.response.compare;

import lombok.Data;

import java.util.List;

@Data
public class ComparePaymentinsAllResponse extends CompareResponse{

    private String IsSuccess;
    private String Msg;
    private List<ComparePaymentResponse> ComparePayment;
}
