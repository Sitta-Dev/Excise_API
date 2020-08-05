package com.xcs.phase2.model.adjust;

import lombok.Data;

@Data
public class AdjustComparePaymentDetail extends AdjustModel {

    private int PAYMENT_DETAIL_ID;
    private int PAYMENT_ID;
    private int NOTICE_ID;
    private int IS_REQUEST_BRIBE;
    private int IS_ACTIVE;

}
