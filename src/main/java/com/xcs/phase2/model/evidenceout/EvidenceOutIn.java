package com.xcs.phase2.model.evidenceout;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceOutIn extends EvidenceOutModel {

    private int EVIDENCE_IN_ID;
    private int PROVE_ID;
    private int ARREST_ID;
    private String DELIVERY_CODE;
    private String DELIVERY_NO;
    private String DELIVERY_DATE;
    private String DELIVERY_OFFICE_CODE;
    private String DELIVERY_OFFICE_NAME;
    private String DELIVERY_TITTLE;
    private String DELIVERY_DEAR;
    private String RETURN_DATE;
    private String REMARK;
    private int DELIVERY_TYPE;
    private String EVIDENCE_IN_CODE;
    private String EVIDENCE_IN_DATE;
    private int RECEIVE_OFFICE_CODE;
    private String RECEIVE_OFFICE_NAME;
    private String COMMENT1;
    private int IS_RECEIVE;
    private int EVIDENCE_IN_TYPE;
    private int IS_EDIT;
    private int IS_ACTIVE;
    private List<EvidenceOutInItem> EvidenceOutInItem;

}
