package com.xcs.phase2.request.target;

import lombok.Data;

@Data
public class TargetListgetByConAdvReq extends TargetRequest{

    private String TARGET_CODE;
    private String IS_SEND;
    private String BUDGET_YEAR_FROM;
    private String BUDGET_YEAR_TO;
    private String SEQUENCE;
    private String TARGET_ITEM_DATE_FROM;
    private String TARGET_ITEM_DATE_TO;
    private String OFFICE_NAME;
    private String PRODUCT_GROUP_NAME;
    private String LAWSUIT_TYPE_TARGET;
    private String OFFICE_CODE;
}
