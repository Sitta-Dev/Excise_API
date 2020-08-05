package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class RevenueCompareDetail extends RevenueModel {

    private int COMPARE_DETAIL_ID;
    private int COMPARE_MAPPING_ID;
    private int RECEIPT_OFFICE_ID;
    private int APPROVE_OFFICE_ID;
    private int MISTREAT_NO;
    private float OLD_PAYMENT_FINE;
    private float PAYMENT_FINE;
    private float DIFFERENCE_PAYMENT_FINE;
    private float TREASURY_MONEY;
    private float BRIBE_MONEY;
    private float REWARD_MONEY;
    private String PAYMENT_FINE_DUE_DATE;
    private String PAYMENT_VAT_DUE_DATE;
    private String INSURANCE;
    private String GAURANTEE;
    private String PAYMENT_DATE;
    private int RECEIPT_TYPE;
    private String RECEIPT_BOOK_NO;
    private int RECEIPT_NO;
    private String RECEIPT_OFFICE_CODE;
    private String RECEIPT_OFFICE_NAME;
    private String APPROVE_OFFICE_CODE;
    private String APPROVE_OFFICE_NAME;
    private String APPROVE_DATE;
    private String APPROVE_TYPE;
    private String COMMAND_NO;
    private String COMMAND_DATE;
    private String REMARK_NOT_AGREE;
    private String REMARK_NOT_APPROVE;
    private String FACT;
    private String COMPARE_REASON;
    private String ADJUST_REASON;
    private String COMPARE_TYPE;
    private int IS_REQUEST;
    private int IS_TEMP_RELEASE;
    private int IS_INSURANCE;
    private int IS_GAURANTEE;
    private String IS_PAYMENT;
    private String IS_REVENUE;
    private int IS_AGREE;
    private int IS_APPROVE;
    private int IS_AUTHORITY;
    private int IS_ACTIVE;
    private String DID;
    private List<RevenuePayment> RevenuePayment;
    private List<RevenueCompareDetailFine> RevenueCompareDetailFine;

}
