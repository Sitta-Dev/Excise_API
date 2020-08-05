package com.xcs.phase2.request.evidence;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class EvidenceAccountWarehoustgetByConReq extends Request {

    private String OFFICE_CODE;
    private String WAREHOUSE_ID;
}
