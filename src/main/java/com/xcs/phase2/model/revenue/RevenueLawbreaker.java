package com.xcs.phase2.model.revenue;

import lombok.Data;

@Data
public class RevenueLawbreaker extends RevenueModel {

    private int LAWBREAKER_ID;
    private int ARREST_ID;
    private int PERSON_ID;
    private int TITLE_ID;
    private int PERSON_TYPE;
    private int ENTITY_TYPE;
    private String TITLE_NAME_TH;
    private String TITLE_NAME_EN;
    private String TITLE_SHORT_NAME_TH;
    private String TITLE_SHORT_NAME_EN;
    private String FIRST_NAME;
    private String MIDDLE_NAME;
    private String LAST_NAME;
    private String OTHER_NAME;
    private int IS_ACTIVE;
    private String COMPANY_NAME;

}
