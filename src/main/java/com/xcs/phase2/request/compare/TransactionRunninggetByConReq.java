package com.xcs.phase2.request.compare;

import lombok.Data;

@Data
public class TransactionRunninggetByConReq extends CompareRequest{

    private String RUNNING_TABLE;
    private String RUNNING_OFFICE_CODE;
}
