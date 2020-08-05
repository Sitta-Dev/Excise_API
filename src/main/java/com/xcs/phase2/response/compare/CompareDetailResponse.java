package com.xcs.phase2.response.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareDetailResponse extends CompareResponse{

    private int COMPARE_DETAIL_ID;
    private List<CompareStaffResponse> CompareStaff;
    private List<CompareDetailPaymentResponse> CompareDetailPayment;
    private List<CompareDetailFineResponse> CompareDetailFine;
    private List<ComparePaymentResponse> ComparePayment;
}
