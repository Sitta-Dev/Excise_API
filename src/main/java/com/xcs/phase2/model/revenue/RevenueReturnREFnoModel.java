package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class RevenueReturnREFnoModel extends RevenueModel {


    private int REVENUE_ID;
    private String RECEIVE_REF_NO;
    private String RECEIVE_DATE;
    private List<RevenueDetailModel> REVENUE_DETAIL;
    private List<RevenuePaymentModel> PAYMENT;
    private List<RevenueCompareDetailModel> COMPARE_DETAIL;

}
