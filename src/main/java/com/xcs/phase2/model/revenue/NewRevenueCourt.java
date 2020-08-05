package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class NewRevenueCourt extends RevenueModel {

    private int REVENUE_ID;
    private int LAWSUIT_ID;
    private int INDICTMENT_ID;
    private int OFFICE_ID;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private String LAWSUILT_NO;
    private int IS_LAWSUIT;
    private String LAWSUIT_DATE;
    private int IS_OUTSIDE;
    private List<NewRevenueLawsuitDetail> RevenueLawsuitDetail;

}
