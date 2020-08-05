package com.xcs.phase2.request.revenue;

import lombok.Data;

@Data
public class RevenuegetByConAdvReq extends RevenueRequest {

    private String REVENUE_CODE;
    private String REVENUE_DATE_FROM;
    private String REVENUE_DATE_TO;
    private String DELIVERY_OFFICE_NAME;
    private String STAFF_NAME_SEND;
    private String STAFF_NAME_SIGN;
    private String LAWSUILT_IS_OUTSIDE;
    private String COMPARE_NO;
    private String COMPARE_NO_YEAR;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String COMPARE_IS_OUTSIDE;
    private String REVENUE_STATUS;
    private String ACCOUNT_OFFICE_CODE;

}
