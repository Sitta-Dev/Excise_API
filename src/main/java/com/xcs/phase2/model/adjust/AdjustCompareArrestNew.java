package com.xcs.phase2.model.adjust;

import lombok.Data;

import java.util.List;

@Data
public class AdjustCompareArrestNew extends AdjustModel {

    private int COMPARE_ID;
    private String COMPARE_NO;
    private String COMPARE_NO_YEAR;
    private String COMPARE_DATE;
    private String COMPARE_STAFF;
    private String OPREATION_POS_NAME;
    private int INDICTMENT_ID;
    private int ARREST_ID;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String ACCUSER_NAME;
    private String ACCUSER_OPREATION_POS_NAME;
    private String ACCUSER_OPERATION_OFFICE_NAME;
    private String LOACTION_NAME;
    private int LAWSUIT_ID;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_IS_OUTSIDE;
    private String LAWSUIT_DATE;
    private int PROVE_ID;
    private String PROVE_NO;
    private String PROVE_NO_YEAR;
    private String PROVE_IS_OUTSIDE;
    private String RECEIVE_DOC_DATE;
    private String GUILTBASE_NAME;
    private String SUBSECTION_RULE_ID;
    private String FINE_TYPE;
    private String SUBSECTION_ID;
    private String SUBSECTION_NAME;
    private String SUBSECTION_DESC;
    private String SECTION_NAME;
    private String PENALTY_DESC;


    private List<AdjustCompareArrestIndictmentDetail> AdjustCompareArrestIndictmentDetail;
    private List<AdjustCompareProveProduct> AdjustCompareProveProduct;
    private List<AdjustCompareGuiltbaseFine> AdjustCompareGuiltbaseFine;

}

