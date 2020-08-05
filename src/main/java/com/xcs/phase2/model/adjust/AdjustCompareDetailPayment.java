package com.xcs.phase2.model.adjust;

import lombok.Data;

@Data
public class AdjustCompareDetailPayment extends AdjustModel {

    private int PAYMENT_ID;
    private int COMPARE_DETAIL_ID;
    private int PAYMENT_TYPE;
    private float PAYMENT_FINE;
    private String REFFERENCE_NO;
    private int IS_ACTIVE;

}
