package com.xcs.phase2.model.document;

import lombok.Data;

@Data
public class PersonRelationship extends DocumentModel {

    private int LAWBREAKER_ID;
    private String PHOTO;
    private String ARREST_LAWBREAKER_NAME;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String ADDRESS;
    private String VILLAGE;
    private String BUILDING;
    private String ROOM;
    private String FLOOR;
    private String VILLAGE_NAME;
    private String ALLEY;
    private String LANE;
    private String ROAD;
    private String PROVINCE;
    private int GUILTBASE_ID;
    private String GUILTBASE_NAME;
    private String PRODUCT_GROUP_NAME;

}
