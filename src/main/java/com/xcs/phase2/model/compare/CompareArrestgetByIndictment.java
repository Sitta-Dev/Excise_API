package com.xcs.phase2.model.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareArrestgetByIndictment extends CompareModel {

    private int INDICTMENT_ID;
    private int LAWSUIT_ID;
    private int PROVE_ID;
    private int ARREST_ID;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String OFFICE_NAME;
    private String ARREST_STAFF_NAME;
    private String OPREATION_POS_NAME;
    private String OPERATION_OFFICE_NAME;
    private String OPERATION_OFFICE_SHORT_NAME;
    private String SUB_DISTRICT_NAME_TH;
    private String SUB_DISTRICT_NAME_EN;
    private String DISTRICT_NAME_TH;
    private String DISTRICT_NAME_EN;
    private String PROVINCE_NAME_TH;
    private String PROVINCE_NAME_EN;
    private String SECTION_NAME;
    private String SUBSECTION_ID;
    private String SUBSECTION_NAME;
    private String GUILTBASE_NAME;
    private int SECTION_ID;
    private String PENALTY_DESC;
    private String FINE_TYPE;
    private String FINE_RATE_MIN;
    private String FINE_RATE_MAX;
    private String FINE_MIN;
    private String FINE_MAX;
    private String LAWSUIT_IS_OUTSIDE;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE;
    private int PROVE_IS_OUTSIDE;
    private String PROVE_NO;
    private String PROVE_NO_YEAR;
    private String RECEIVE_DOC_DATE;

    private List<NewCompareArrestIndictmentDetail> CompareArrestIndictmentDetail;
    private List<CompareGuiltbaseFine> CompareGuiltbaseFine;

}
