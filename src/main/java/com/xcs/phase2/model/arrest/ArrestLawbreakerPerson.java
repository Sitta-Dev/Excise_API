package com.xcs.phase2.model.arrest;

import lombok.Data;

@Data
public class ArrestLawbreakerPerson extends ArrestModel {

    private int LAWBREAKER_ID;
    private int PERSON_ID;
    private String PHOTO;
    private String ARREST_LAWBREAKER_NAME;
    private String ARREST_CODE;
    private String OCCURRENCE_DATE;
    private String SECTION_NAME;
    private String GUILTBASE_NAME;
    private String PRODUCT_GROUP_NAME;
    private int DOCUMENT_ID;
    private String FILE_PATH;


}
