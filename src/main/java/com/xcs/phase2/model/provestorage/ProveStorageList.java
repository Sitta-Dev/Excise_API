package com.xcs.phase2.model.provestorage;

import lombok.Data;

@Data
public class ProveStorageList extends ProveStorageModel {

    private String ARREST_ID;
    private String ARREST_CODE;
    private String ARREST_DATE;
    private String LAWSUIT_NO;
    private String DELIVERY_NO;
    private String DELIVERY_DATE;
    private String DELIVERY_FULL_NAME;
    private String DELIVERY_OFFICE_NAME;
    private String EVIDENCE_IN_CODE;
    private String EVIDENCE_IN_DATE;
    private String PROVE_FULL_NAME;
    private String RECEIVE_OFFICE_NAME;
    private int IS_RECEIVE;
    private String IS_RECEIVE_TEXT;
    private int EVIDENCE_IN_ID;



}
