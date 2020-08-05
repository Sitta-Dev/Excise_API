package com.xcs.phase2.model.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutDetail extends EvidenceOutModel {

    private int EVIDENCE_OUT_DETAIL_ID;
    private int EVIDENCE_OUT_ID;
    private int EVIDENCE_IN_ID;
    private int IS_ACTIVE;
    private EvidenceOutIn EvidenceOutIn;

}
