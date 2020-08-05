package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class RevenueList extends RevenueModel {

    private int REVENUE_ID;
    private int DELIVERY_OFFICE_ID;
    private int RECEIVE_OFFICE_ID;
    private String REVENUE_CODE;
    private String DELIVERY_OFFICE_CODE;
    private String DELIVERY_OFFICE_NAME;
    private String RECEIVE_OFFICE_CODE;
    private String RECEIVE_OFFICE_NAME;
    private String REVENUE_NO;
    private String REVENUE_DATE;
    private int REVENUE_STATUS;
    private String REVENUE_COUNT;
    private float FINE;
    private float TREASURY_MONEY;
    private float BRIBE_MONEY;
    private float REWARD_MONEY;
    private String RECEIVE_REF_NO;
    private String RECEIVE_DATE;
    private int IS_ACTIVE;
    private List<RevenueDetail> RevenueDetail;
    private List<RevenueStaff> RevenueStaff;

}
