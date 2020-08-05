package com.xcs.phase2.model.evidenceout;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceOutList extends EvidenceOutModel {

    private int EVIDENCE_OUT_ID;
    private String EVIDENCE_OUT_CODE;
    private String EVIDENCE_OUT_DATE;
    private String EVIDENCE_OUT_TYPE;
    private String EVIDENCE_OUT_NO;
    private String EVIDENCE_OUT_NO_DATE;
    private String BOOK_NO;
    private String RECEIPT_NO;
    private String PAY_DATE;
    private String APPROVE_DATE;
    private String RETURN_DATE;
    private String APPROVE_NO;
    private int EVIDENCE_IN_ID;
    private String SEND_TO_OFFICE_CODE;
    private String SEND_TO_OFFICE_NAME;
    private String TEXT_EVIDENCE_OUT_DATE;
    private String TEXT_APPROVE_DATE;
    private List<EvidenceOutStaff> EvidenceOutStaff;


}
