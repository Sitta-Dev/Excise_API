package com.xcs.phase2.model.evidence;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class EvidenceAccountProductDetail extends Request {

    private int EVIDENCE_IN_ITEM_ID;
    private String EVIDENCE_IN_ITEM_CODE;
    private String EVIDENCE_IN_DATE;
    private String NAME;
}
