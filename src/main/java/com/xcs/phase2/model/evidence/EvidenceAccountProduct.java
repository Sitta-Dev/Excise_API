package com.xcs.phase2.model.evidence;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class EvidenceAccountProduct extends Request {

    private int EVIDENCE_OUT_ID;
    private String EVIDENCE_OUT_CODE;
    private String EVIDENCE_OUT_DATE;
}
