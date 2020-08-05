package com.xcs.phase2.model.provestorage;

import lombok.Data;

@Data
public class ProveStorageEvidenceInStaff extends ProveStorageModel {

    private int STAFF_ID;
    private int EVIDENCE_IN_STAFF_ID;
    private String TITLE_SHORT_NAME_TH;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String MANAGEMENT_POS_NAME;
    private String OPERATION_OFFICE_SHORT_NAME;
    private int CONTRIBUTOR_ID;

}
