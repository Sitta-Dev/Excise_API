package com.xcs.phase2.request.evidence;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class EvidenceAccountProductDetailgetByConReq extends Request {

    private int EVIDENCE_OUT_ID;
    private String OFFICE_CODE;
    private String WAREHOUSE_ID;
}
