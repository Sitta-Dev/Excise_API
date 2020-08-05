package com.xcs.phase2.model.evidencein;

import lombok.Data;

@Data
public class TransactionRunningItem extends EvidenceInModel {

    private int RUNNING_ID;
    private String RUNNING_YEAR;
    private String RUNNING_MONTH;
    private String RUNNING_PREFIX;
    private int PRODUCT_GROUP_ID;
    private int WAREHOUSE_ID;
    private int RUNNING_NO;

}
