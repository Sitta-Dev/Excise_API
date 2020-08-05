package com.xcs.phase2.model.evidencein;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceInOut extends EvidenceInModel {

    private int EVIDENCE_OUT_ID;
    private String EVIDENCE_OUT_DATE;
    private String EVIDENCE_OUT_TIME;
    private int EVIDENCE_OUT_TYPE;
    private String EVIDENCE_OUT_NO;
    private String EVIDENCE_OUT_NO_DATE;
    private String EVIDENCE_OUT_NO_TIME;
    private String BOOK_NO;
    private String RECEIPT_NO;
    private String PAY_DATE;
    private String PAY_TIME;
    private String APPROVE_DATE;
    private String APPROVE_TIME;
    private String RETURN_DATE;
    private String REMARK;
    private int WAREHOUSE_ID;
    private String APPROVE_NO;
    private String EVIDENCE_OUT_CODE;
    private int OFFICE_CODE;
    private int IS_ACTIVE;
    private List<EvidenceInOutItem> EvidenceInOutItem;

}
