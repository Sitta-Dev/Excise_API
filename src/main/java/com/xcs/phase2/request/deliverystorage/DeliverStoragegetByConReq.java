package com.xcs.phase2.request.deliverystorage;

import com.xcs.phase2.request.compare.CompareRequest;
import lombok.Data;

@Data
public class DeliverStoragegetByConReq extends CompareRequest {

    private String EVIDENCE_IN_ID;
}
