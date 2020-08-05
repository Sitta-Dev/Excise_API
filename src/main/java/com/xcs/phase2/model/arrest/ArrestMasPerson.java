package com.xcs.phase2.model.arrest;

import lombok.Data;

import java.util.List;

@Data
public class ArrestMasPerson extends ArrestModel {


    private int PERSON_ID;
    private int PERSON_TYPE;
    private int ENTITY_TYPE;
    private String ID_CARD;
    private String PASSPORT_NO;
    private String COMPANY_REGISTRATION_NO;
    private String COMPANY_NAME;
    private int TITLE_ID;
    private String TITLE_NAME_TH;
    private String TITLE_NAME_EN;
    private String TITLE_SHORT_NAME_TH;
    private String TITLE_SHORT_NAME_EN;
    private String FIRST_NAME;
    private String MIDDLE_NAME;
    private String LAST_NAME;
    private String OTHER_NAME;
    private String EXCISE_REGISTRATION_NO;
    private String COUNTRY_NAME_TH;
    private String COUNTRY_NAME_EN;
    private int MISTREAT_NO;
    private List<ArrestMasPersonRelationship> ArrestMasPersonRelationship;
}
