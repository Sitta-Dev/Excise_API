package com.xcs.phase2.model.evidencein;

import lombok.Data;

@Data
public class EvidenceInArrest extends EvidenceInModel {

    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String OFFICE_NAME;
    private String ARREST_STAFF;
    private String ARREST_OPREATION_POS_NAME;
    private String ARREST_OPERATION_OFFICE_NAME;
    private int SUBSECTION_RULE_ID;
    private int SUBSECTION_ID;
    private String SUBSECTION_NAME;
    private String SUBSECTION_NO;
    private int GUILTBASE_ID;
    private String GUILTBASE_NAME;
    private int SECTION_ID;
    private String PENALTY_DESC;
    private int LAWSUIT_ID;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE;
    private String LAWSUIT_STAFF;
    private String LAWSUIT_OPREATION_POS_NAME;
    private String LAWSUIT_OPERATION_OFFICE_NAME;
    private int LAWSUIT_IS_OUTSIDE;
    private int PROVE_ID;
    private String PROVE_NO;
    private String PROVE_NO_YEAR;
    private String PROVE_DATE;
    private String PROVE_STAFF;
    private String PROVE_OPREATION_POS_NAME;
    private int PROVE_IS_OUTSIDE;
    private String RECEIVE_OFFICE_NAME;
    private String DELIVERY_OFFICE_NAME;
    private String DELIVERY_DOC_NO_1;
    private String DELIVERY_DOC_NO_2;
    private String DELIVERY_DOC_DATE;
    private String EVIDENCE_IN_STAFF;
    private String EVIDENCE_IN_OPREATION_POS_NAME;
    private String EVIDENCE_IN_OPERATION_OFFICE_NAME;
    private String OPERATION_OFFICE_SHORT_NAME;


}
