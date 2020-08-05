package com.xcs.phase2.model.revenue;

import lombok.Data;

@Data
public class NewRevenueCompareDetail extends RevenueModel {

    private int COMPARE_ID;
    private int LAWSUIT_ID;
    private int OFFICE_ID;
    private int COMPARE_DETAIL_ID;
    private int COMPARE_MAPPING_ID;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private int COMPARE_IS_OUTSIDE;
    private String COMPARE_NO;
    private String COMPARE_DATE;
    private int IS_ACTIVE;


}
