package com.xcs.phase2.request.evidence;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class EvidenceAccountProductgetByConReq extends Request {

    private String EVIDENCE_OUT_TYPE;
    private String OFFICE_CODE;
    private String WAREHOUSE_ID;
}
