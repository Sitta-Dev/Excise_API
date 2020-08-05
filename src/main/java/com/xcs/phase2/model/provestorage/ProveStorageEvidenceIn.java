package com.xcs.phase2.model.provestorage;

import lombok.Data;

import java.util.List;

@Data
public class ProveStorageEvidenceIn extends ProveStorageModel {

    private int EVIDENCE_IN_ID;
    private String EVIDENCE_IN_CODE;
    private String EVIDENCE_IN_DATE;
    private String EVIDENCE_IN_TIME;
    private String RECEIVE_OFFICE_CODE;
    private String RECEIVE_OFFICE_NAME;
    private String COMMENT1;
    private List<ProveStorageEvidenceInItem> ProveStorageEvidenceInItem;
    private List<ProveStorageEvidenceInStaff> ProveStorageEvidenceInStaff;












}
