package com.xcs.phase2.response.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareDetailinsAllResponse extends CompareResponse{

    private String IsSuccess;
    private String Msg;
    private int COMPARE_DETAIL_ID;
    private List<CompareDetailPaymentResponse> CompareDetailPayment;
    private List<CompareDetailFineResponse> CompareDetailFine;
    private List<ComparePaymentResponse> ComparePayment;
}
