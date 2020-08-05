package com.xcs.phase2.model.revenue;

import lombok.Data;

@Data
public class RevenueDetail extends RevenueModel {

    private int REVENUE_DETAIL_ID;
    private int REVENUE_ID;
    private int COMPARE_DETAIL_ID;
    private int REVENUE_STATUS;
    private int IS_ACTIVE;
    private int LAWSUIT_DETAIL_ID;
    private int PAYMENT_ID;
    private RevenueCompareDetail RevenueCompareDetail;

}
