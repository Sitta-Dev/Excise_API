package com.xcs.phase2.model.arrest;

import lombok.Data;

@Data
public class ArrestPerson extends ArrestModel {

    private int LAWBREAKER_ID;
    private int PERSON_ID;
    private String PHOTO;
    private String ARREST_LAWBREAKER_NAME;
    private int ARREST_ID;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String ADDRESS_NO;
    private String VILLAGE_NO;
    private String BUILDING_NAME;
    private String ROOM_NO;
    private String FLOOR;
    private String VILLAGE_NAME;
    private String ALLEY;
    private String LANE;
    private String ROAD;
    private String LOCATION;
    private String SECTION_NAME;
    private String GUILTBASE_NAME;
    private String PRODUCT_GROUP_NAME;
    private int DOCUMENT_ID;
    private String FILE_PATH;


}
