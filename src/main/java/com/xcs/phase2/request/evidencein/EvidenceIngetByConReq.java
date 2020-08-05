package com.xcs.phase2.request.evidencein;

import lombok.Data;

@Data
public class EvidenceIngetByConReq extends EvidenceInRequest {

    private int EVIDENCE_IN_ID;
    private int PROVE_ID;
}
