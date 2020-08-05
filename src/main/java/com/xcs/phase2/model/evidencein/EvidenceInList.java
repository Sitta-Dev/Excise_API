package com.xcs.phase2.model.evidencein;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceInList extends EvidenceInModel {


    private int EVIDENCE_IN_ID;
    private int PROVE_ID;
    private String EVIDENCE_IN_CODE;
    private String EVIDENCE_IN_DATE;
    private String INSPECTOR;
    private String INSPECTOR_OPERATION_OFFICE_NAME;
    private String DELIVERY_NO;
    private String DELIVERY_DATE;
    private String CARRIER;
    private String CARRIER_OPERATION_OFFICE_NAME;
    private String WAREHOUSE_NAME;
    private int EVIDENCE_IN_TYPE;

    private String LAWSUIT_NO;
    private String LAWSUIT_YEAR;
    private String LAWSUIT_DATE;
    private String LAWSUIT_STAFF;

    private String PROVE_NO;
    private String PROVE_DATE;
    private String PROVE_STAFF;
    private int IS_ACTIVE;
    private List<EvidenceInStaff> EvidenceInStaff;



}
