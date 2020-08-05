package com.xcs.phase2.model.revenue;

import lombok.Data;

@Data
public class RevenueCompareDetailFine extends RevenueModel {

    private int FINE_ID;
    private int COMPARE_DETAIL_ID;
    private int PRODUCT_ID;
    private int PRODUCT_GROUP_ID;
    private String PRODUCT_GROUP_CODE;

    private float FINE_RATE;
    private float VAT;
    private float FINE;
    private float NET_FINE;
    private float OLD_PAYMENT_FINE;
    private float PAYMENT_FINE;
    private float DIFFERENCE_PAYMENT_FINE;
    private float TREASURY_MONEY;
    private float BRIBE_MONEY;
    private float REWARD_MONEY;
    private int IS_ACTIVE;


}
