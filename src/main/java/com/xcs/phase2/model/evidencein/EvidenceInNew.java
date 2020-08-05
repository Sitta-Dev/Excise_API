package com.xcs.phase2.model.evidencein;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceInNew extends EvidenceInModel {

    private int EVIDENCE_IN_ID;
    private int PROVE_ID;
    private String EVIDENCE_IN_CODE;
    private String EVIDENCE_IN_DATE;
    private String RETURN_DATE;
    private int IS_RECEIVE;
    private String DELIVERY_NO;
    private String DELIVERY_DATE;
    private int EVIDENCE_IN_TYPE;
    private String REMARK;
    private int IS_ACTIVE;
    private int IS_EDIT;
    private List<EvidenceInOutNew> EvidenceInOut;
}
