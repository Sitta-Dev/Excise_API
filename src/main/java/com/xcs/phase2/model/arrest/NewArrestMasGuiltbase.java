package com.xcs.phase2.model.arrest;

import lombok.Data;

@Data
public class NewArrestMasGuiltbase extends ArrestModel {


    private int GUILTBASE_ID;
    private String GUILTBASE_NAME;
    private String FINE;
    private int IS_PROVE;
    private int IS_COMPARE;
    private String SUBSECTION_NAME;
    private String SUBSECTION_DESC;
    private String SECTION_NAME;
    private String PENALTY_DESC;


}
