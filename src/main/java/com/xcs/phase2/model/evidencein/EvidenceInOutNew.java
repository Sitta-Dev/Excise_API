package com.xcs.phase2.model.evidencein;

import lombok.Data;

@Data
public class EvidenceInOutNew extends EvidenceInModel {

    private int EVIDENCE_OUT_ID;
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
    private int WAREHOUSE_ID;
    private String APPROVE_NO;
    private String EVIDENCE_OUT_CODE;
    private int OFFICE_CODE;
    private int IS_ACTIVE;
    private int EVIDENCE_IN_ID;

}
