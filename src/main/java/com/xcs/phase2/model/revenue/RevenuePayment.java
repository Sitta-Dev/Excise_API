package com.xcs.phase2.model.revenue;

import lombok.Data;

@Data
public class RevenuePayment extends RevenueModel {

    private int PAYMENT_ID;
    private int LAWSUIT_DETAIL_ID;
    private int COMPARE_DETAIL_ID;
    private int FINE_TYPE;
    private float FINE;
    private int PAYMENT_PERIOD_NO;
    private String PAYMENT_DATE;
    private int IS_REQUEST_REWARD;
    private int IS_ACTIVE;
    private int PAYMENT_CHANNEL;
    private String PAYMENT_BANK;
    private String PAYMENT_REF_NO;
    private int IS_REVENUE;
}
