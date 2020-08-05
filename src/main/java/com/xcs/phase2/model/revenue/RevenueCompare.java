package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class RevenueCompare extends RevenueModel {

    private int COMPARE_ID;
    private int LAWSUIT_ID;
    private int OFFICE_ID;
    private float TREASURY_RATE;
    private float BRIBE_RATE;
    private float REWARD_RATE;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private int IS_OUTSIDE;
    private String COMPARE_IS_OUTSIDE;
    private String COMPARE_NO;
    private String COMPARE_DATE;
    private int IS_ACTIVE;
    private int IS_TEMP_RELEASE;
    private List<RevenueCompareMapping> RevenueCompareMapping;







}
