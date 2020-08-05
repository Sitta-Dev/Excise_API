package com.xcs.phase2.model.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareList extends CompareModel {

    private int COMPARE_ID;
    private int LAWSUIT_ID;
    private int ARREST_ID;
    private int INDICTMENT_ID;
    private String ARREST_CODE;
    private String ARREST_DATE;
    private String OCCURRENCE_DATE;
    private String ARREST_STAFF_NAME;
    private String OPREATION_POS_NAME;
    private String OPERATION_OFFICE_NAME;
    private String OPERATION_OFFICE_SHORT_NAME;
    private String ARREST_OFFICE_NAME;
    private String SUB_DISTRICT_NAME_TH;
    private String SUB_DISTRICT_NAME_EN;
    private String DISTRICT_NAME_TH;
    private String DISTRICT_NAME_EN;
    private String PROVINCE_NAME_TH;
    private String PROVINCE_NAME_EN;
    private String SECTION_NAME;
    private int SUBSECTION_ID;
    private String SUBSECTION_NAME;
    private String GUILTBASE_NAME;
    private int SECTION_ID;
    private String PENALTY_DESC;
    private String LAWSUIT_STAF_NAME;
    private String LAWSUIT_OPREATION_POS_NAME;
    private String LAWSUIT_OPERATION_OFFICE_NAME;
    private String LAWSUIT_OPERATION_OFFICE_SHORT_NAME;
    private String LAWSUIT_OFFICE_NAME;
    private int LAWSUIT_IS_OUTSIDE;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE;
    private String LAWSUIT_TYPE;
    private String LAWSUIT_END;
    private String PROVE_STAFF_NAME;
    private String PROVE_OPREATION_POS_NAME;
    private String PROVE_OPERATION_OFFICE_NAME;
    private String PROVE_OPERATION_OFFICE_SHORT_NAME;
    private int PROVE_IS_OUTSIDE;
    private String PROVE_NO;
    private String PROVE_NO_YEAR;
    private String RECEIVE_DOC_DATE;
    private String RECEIVE_OFFICE_NAME;
    private String COMPARE_STAFF_NAME;
    private String COMPARE_OPREATION_POS_NAME;
    private String COMPARE_OPERATION_OFFICE_NAME;
    private String COMPARE_OPERATION_OFFICE_SHORT_NAME;
    private int COMPARE_IS_OUTSIDE;
    private String COMPARE_NO;
    private String COMPARE_NO_YEAR;
    private String COMPARE_DATE;
    private String COMPARE_OFFICE_NAME;
    private String COMPARE_STATUS;
    private int PROVE_IS_ACTIVE;
    private int IS_PROVE;
    private List<com.xcs.phase2.model.arrest.ArrestLawbreaker> ArrestLawbreaker;
}
