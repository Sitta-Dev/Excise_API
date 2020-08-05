package com.xcs.phase2.model.prove;

import lombok.Data;

import java.util.List;

@Data
public class ProveArrest extends ProveModel {

    private int ARREST_ID;
    private String ARREST_CODE;
    private String ARREST_DATE;
    private String OFFICE_NAME;
    private String ARREST_STAFF_NAME;
    private String ARREST_OPREATION_POS_NAME;
    private String ARREST_OPERATION_OFFICE_NAME;
    private String ARREST_OPERATION_OFFICE_SHORT_NAME;
    private String ARREST_MANAGEMENT_POS_NAME;
    private String ARREST_MANAGEMENT_OFFICE_NAME;
    private String ARREST_MANAGEMENT_OFFICE_SHORT_NAME;
    private String ARREST_REPRESENT_POS_NAME;
    private String ARREST_REPRESENT_OFFICE_NAME;
    private String ARREST_REPRESENT_OFFICE_SHORT_NAME;
    private String SECTION_NAME;
    private String GUILTBASE_NAME;
    private int SECTION_ID;
    private String PENALTY_DESC;
    private int LAWSUIT_ID;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE;
    private String DELIVERY_DOC_NO_1;
    private String DELIVERY_DOC_NO_2;



    private List<ProveLawsuitStaff> ProveLawsuitStaff;
    private List<ProveLawsuitType> ProveLawsuitType;

}
