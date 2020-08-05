package com.xcs.phase2.model.evidencein;

import com.xcs.phase2.model.evidenceout.EvidenceOutStaff;
import lombok.Data;

import java.util.List;

@Data
public class EvidenceInBook extends EvidenceInModel {

    private String EVIDENCE_OUT_ID;
    private String OFFICE_CODE;
    private String EVIDENCE_OUT_CODE;
    private String EVIDENCE_OUT_DATE;
    private int EVIDENCE_OUT_TYPE;
    private String EVIDENCE_OUT_NO;
    private String EVIDENCE_OUT_NO_DATE;
    private String BOOK_NO;
    private String RECEIPT_NO;
    private String PAY_DATE;
    private String APPROVE_DATE;
    private String RETURN_DATE;
    private String REMARK;
    private String APPROVE_NO;
    private int IS_ACTIVE;
    private int EVIDENCE_IN_ID;
    private int WAREHOUSE_ID;
    private List<EvidenceOutStaff> EvidenceOutStaff;


}
