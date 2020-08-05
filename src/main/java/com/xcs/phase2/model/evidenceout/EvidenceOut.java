package com.xcs.phase2.model.evidenceout;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceOut extends EvidenceOutModel {

    private int EVIDENCE_OUT_ID;
    private int OFFICE_CODE;
    private String EVIDENCE_OUT_CODE;
    private String EVIDENCE_OUT_DATE;
    private String EVIDENCE_OUT_TIME;
    private int EVIDENCE_OUT_TYPE;
    private String EVIDENCE_OUT_NO;
    private String EVIDENCE_OUT_NO_DATE;
    private String EVIDENCE_OUT_NO_TIME;
    private String BOOK_NO;
    private String RECEIPT_NO;
    private String PAY_DATE;
    private String APPROVE_DATE;
    private String APPROVE_TIME;
    private String RETURN_DATE;
    private String REMARK;
    private String APPROVE_NO;
    private int IS_ACTIVE;
    private int EVIDENCE_IN_ID;
    private int WAREHOUSE_ID;
    private String DELIVERY;
    private String REMARK_CANCEL;
    private int SEND_TO_OFFICE_CODE;
    private String SEND_TO_OFFICE_NAME;

    private List<EvidenceOutDetail> EvidenceOutDetail;
    private List<EvidenceOutItem> EvidenceOutItem;
    private List<EvidenceOutStaff> EvidenceOutStaff;



}
