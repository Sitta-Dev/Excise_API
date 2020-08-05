package com.xcs.phase2.model.deliverystorage;

import lombok.Data;

@Data
public class
DeliveryStorageEvidenceInStaff extends DeliveryStorageModel {

    private int STAFF_ID;
    private int EVIDENCE_IN_STAFF_ID;
    private String TITLE_SHORT_NAME_TH;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String MANAGEMENT_POS_NAME;
    private String OPERATION_OFFICE_SHORT_NAME;
    private int CONTRIBUTOR_ID;

}
