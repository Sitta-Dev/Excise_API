package com.xcs.phase2.model.lawsult;

import lombok.Data;

import java.util.List;

@Data
public class LawsuitList extends LawsultModel {

    private int LAWSUIT_ID;
    private int INDICTMENT_ID;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String ACCUSER_TITLE_NAME_TH;
    private String ACCUSER_TITLE_SHORT_NAME_TH;
    private String ACCUSER_FIRST_NAME;
    private String ACCUSER_LAST_NAME;
    private String ARREST_OFFICE_NAME;
    private String GUILTBASE_NAME;
    private String SUBSECTION_NAME;
    private String SUB_DISTRICT_NAME_TH;
    private String DISTRICT_NAME_TH;
    private String PROVINCE_NAME_TH;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private int LAWSUIT_IS_OUTSIDE;
    private String LAWSUIT_DATE;
    private String LAWSUIT_OPERATION_OFFICE_SHORT_NAME;
    private String LAWSUIT_OFFICE_NAME;
    private String LAWSUIT_TITLE_NAME_TH;
    private String LAWSUIT_TITLE_SHORT_NAME_TH;
    private String LAWSUIT_FIRST_NAME;
    private String LAWSUIT_LAST_NAME;
    private int INDICTMENT_IS_LAWSUIT_COMPLETE;

    private int LAWSUIT_TYPE;
    private int LAWSUIT_END;
    private int IS_LAWSUIT;
    private List<LawsuitLawbreaker> LawsuitLawbreaker;

}
