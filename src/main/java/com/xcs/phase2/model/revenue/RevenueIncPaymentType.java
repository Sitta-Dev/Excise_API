package com.xcs.phase2.model.revenue;

import lombok.Data;

@Data
public class RevenueIncPaymentType extends RevenueModel {

    private int INC_PAYMENT_TYPE_ID;
    private int INC_PAYMENT_ID;
    private int PAYMENT_TYPE;
    private String BANK_CODE;
    private String BRANCH_CODE;
    private String CHEQUE_TYPE;
    private String CHEQUE_FLAG;
    private String CHWQUE_NO;
    private String CHEQUE_DATE;
    private int PAYMENT_AMT;
    private int ADJUST_TYPE;
    private int PAYMENT_ID;
    private int IS_ACTIVE;


}
