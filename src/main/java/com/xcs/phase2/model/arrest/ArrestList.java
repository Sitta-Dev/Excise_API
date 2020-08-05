package com.xcs.phase2.model.arrest;

import lombok.Data;

import java.util.List;

@Data
public class ArrestList extends ArrestModel {

    private int ARREST_ID;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String TITLE_NAME_TH;
    private String TITLE_NAME_EN;
    private String TITLE_SHORT_NAME_TH;
    private String TITLE_SHORT_NAME_EN;
    private String FIRST_NAME;
    private String LAST_NAME;
    //private String SUBSECTION_NAME;
    //private String SUBSECTION_DESC;
    //private String SECTION_ID;
    //private String SECTION_NAME;
    //private String SECTION_DESC_1;
    //private String PENALTY_DESC;
    private String OFFICE_NAME;
    private String SUB_DISTRICT_NAME_TH;
    private String SUB_DISTRICT_NAME_EN;
    private String DISTRICT_NAME_TH;
    private String DISTRICT_NAME_EN;
    private String PROVINCE_NAME_TH;
    private String PROVINCE_NAME_EN;
    private int IS_LAWSUIT_COMPLETE;

    private List<ArrestMasGuiltbase> ArrestMasGuiltbase;
    private List<ArrestLawbreaker> ArrestLawbreaker;


}
