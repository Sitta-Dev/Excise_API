package com.xcs.phase2.model.compare;

import lombok.Data;

@Data
public class TransactionRunning extends CompareModel {

    private int RUNNING_ID;
    private int RUNNING_OFFICE_ID;
    private int RUNNING_NO;
    private String RUNNING_TABLE;
    private String RUNNING_PREFIX;
    private String RUNNING_OFFICE_CODE;
    private String RUNNING_YEAR;
    private String RUNNING_MONTH;

}
