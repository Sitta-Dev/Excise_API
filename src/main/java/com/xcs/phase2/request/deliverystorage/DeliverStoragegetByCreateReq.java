package com.xcs.phase2.request.deliverystorage;

import com.xcs.phase2.request.compare.CompareRequest;
import lombok.Data;

@Data
public class DeliverStoragegetByCreateReq extends CompareRequest {

    private String ARREST_ID;
}
