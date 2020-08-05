package com.xcs.phase2.request.evidencein;

import lombok.Data;

@Data
public class TransactionRunningItemgetByConReq extends EvidenceInRequest {

    private String RUNNING_PREFIX;
    private int PRODUCT_GROUP_ID;
    private int WAREHOUSE_ID;
}
