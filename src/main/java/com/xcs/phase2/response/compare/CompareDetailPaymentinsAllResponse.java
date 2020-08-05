package com.xcs.phase2.response.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareDetailPaymentinsAllResponse extends CompareResponse{

    private String IsSuccess;
    private String Msg;
    private List<CompareDetailPaymentResponse> CompareDetailPayment;
}
