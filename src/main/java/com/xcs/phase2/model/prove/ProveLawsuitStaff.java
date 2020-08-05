package com.xcs.phase2.model.prove;

import lombok.Data;

@Data
public class ProveLawsuitStaff extends ProveModel {

    private String LAWSUIT_STAFF_NAME;
    private String LAWSUIT_OPREATION_POS_NAME;
    private String LAWSUIT_OPERATION_OFFICE_NAME;
    private String LAWSUIT_OPERATION_OFFICE_SHORT_NAME;
    private String LAWSUIT_MANAGEMENT_POS_NAME;
    private String LAWSUIT_MANAGEMENT_OFFICE_NAME;
    private String LAWSUIT_MANAGEMENT_OFFICE_SHORT_NAME;
    private String LAWSUIT_REPRESENT_POS_NAME;
    private String LAWSUIT_REPRESENT_OFFICE_NAME;
    private String LAWSUIT_REPRESENT_OFFICE_SHORT_NAME;
    private int CONTRIBUTOR_ID;

}