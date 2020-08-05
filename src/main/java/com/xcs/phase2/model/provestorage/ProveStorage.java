package com.xcs.phase2.model.provestorage;

import lombok.Data;

import java.util.List;

@Data
public class ProveStorage extends ProveStorageModel {

    private String DELIVERY_CODE;
    private String DELIVERY_OFFICE_NAME;
    private String DELIVERY_NO;
    private String DELIVERY_DATE;
    private String DELIVERY_TIME;
    private String DELIVERY_TITTLE;
    private String DELIVERY_DEAR;
    private String DELIVERY_FULL_NAME;
    private String DELIVERY_OPERATION_POS_NAME;
    private String DELIVERY_OPERATION_OFFICE_SHORT_NAME;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String OCCURRENCE_TIME;
    private String ARREST_STAFF_NAME;
    private String ARREST_OPREATION_POS_NAME;
    private String ARREST_OPERATION_OFFICE_SHORT_NAME;
    private String ARREST_OFFICE_NAME;
    private int EVIDENCE_IN_ID;
    private String DELIVERY_OFFICE_CODE;
    private String OPERATION_OFFICE_NAME;
    private int CONTRIBUTOR_ID;
    private int ARREST_ID;
    private String ARREST_OPERATION_OFFICE_NAME;
    private List<ProveStorageLawsuitDetail> ProveStorageLawsuitDetail;


}
