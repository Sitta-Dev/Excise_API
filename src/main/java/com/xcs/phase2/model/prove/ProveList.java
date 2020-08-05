package com.xcs.phase2.model.prove;

import com.xcs.phase2.model.arrest.ArrestLawbreaker;
import lombok.Data;

import java.util.List;

@Data
public class ProveList extends ProveModel {

    private int ARREST_ID;
    private String ARREST_CODE;
    private String ARREST_DATE;
    private String OCCURRENCE_DATE;
    private int INDICTMENT_ID;
    private String ARREST_STAFF_NAME;
//    private int LAWSUIT_TYPE;
//    private String LAWSUIT_END;
    private String SUBSECTION_NAME;
    private String GUILTBASE_NAME;
    private String SUB_DISTRICT_NAME_TH;
    private String SUB_DISTRICT_NAME_EN;
    private String DISTRICT_NAME_TH;
    private String DISTRICT_NAME_EN;
    private String PROVINCE_NAME_TH;
    private String PROVINCE_NAME_EN;
    private int LAWSUIT_ID;
    private String LAWSUILT_NO;
    private String LAWSUIT_DATE;
    private int IS_LAWSUIT;
    private String LAWSUIT_STAFF_NAME;
    private String OFFICE_NAME;
    private int PROVE_ID;
    private String PROVE_NO;
    private String PROVE_IS_OUTSIDE;
    private String PROVE_DATE;
    private int STAFF_ID;
    private String PROVE_STAFF_NAME;
    private String PROVE_OPERATION_POS_CODE;
    private String PROVE_OPREATION_POS_NAME;
    private String PROVE_OPREATION_POS_LEVEL;
    private String PROVE_OPERATION_POS_LEVEL_NAME;
    private String PROVE_OPERATION_DEPT_CODE;
    private String PROVE_OPERATION_DEPT_NAME;
    private String PROVE_OPERATION_DEPT_LEVEL;
    private String PROVE_OPERATION_UNDER_DEPT_CODE;
    private String PROVE_OPERATION_UNDER_DEPT_NAME;
    private String PROVE_OPERATION_UNDER_DEPT_LEVEL;
    private String PROVE_OPERATION_WORK_DEPT_CODE;
    private String PROVE_OPERATION_WORK_DEPT_NAME;
    private String PROVE_OPERATION_WORK_DEPT_LEVEL;
    private String PROVE_OPERATION_OFFICE_CODE;
    private String PROVE_OPERATION_OFFICE_NAME;
    private String PROVE_OPERATION_OFFICE_SHORT_NAME;
    private String RECEIVE_OFFICE_NAME;
    private String PROVE_STATUS;
    private List<ArrestLawbreaker> ArrestLawbreaker;


}
