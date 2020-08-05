package com.xcs.phase2.model.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareArrestIndictment extends CompareModel {

	private int INDICTMENT_ID;
	private int LAWSUIT_ID;
	private int PROVE_ID;
	private int ARREST_ID;
	private String OFFICE_CODE;
	private String OFFICE_NAME;
	private String ARREST_CODE;
	private String OCCURRENCE_DATE; //
	private String ACCUSER_TITLE_NAME_TH;
	private String ACCUSER_TITLE_NAME_EN;
	private String ACCUSER_TITLE_SHORT_NAME_TH;
	private String ACCUSER_TITLE_SHORT_NAME_EN;
	private String ACCUSER_FIRST_NAME;
	private String ACCUSER_LAST_NAME;
	private String ACCUSER_OPERATION_POS_CODE;
	private String ACCUSER_OPREATION_POS_NAME;
	private String ACCUSER_OPREATION_POS_LEVEL;
	private String ACCUSER_OPERATION_POS_LEVEL_NAME;
	private String ACCUSER_OPERATION_DEPT_CODE;
	private String ACCUSER_OPERATION_DEPT_NAME;
	private int ACCUSER_OPERATION_DEPT_LEVEL;
	private String ACCUSER_OPERATION_UNDER_DEPT_CODE;
	private String ACCUSER_OPERATION_UNDER_DEPT_NAME;
	private int ACCUSER_OPERATION_UNDER_DEPT_LEVEL;
	private String ACCUSER_OPERATION_WORK_DEPT_CODE;
	private String ACCUSER_OPERATION_WORK_DEPT_NAME;
	private int ACCUSER_OPERATION_WORK_DEPT_LEVEL;
	private String ACCUSER_OPERATION_OFFICE_CODE;
	private String ACCUSER_OPERATION_OFFICE_NAME;
	private String ACCUSER_OPERATION_OFFICE_SHORT_NAME;
	private String ACCUSER_MANAGEMENT_POS_CODE;
	private String ACCUSER_MANAGEMENT_POS_NAME;
	private String ACCUSER_MANAGEMENT_POS_LEVEL;
	private String ACCUSER_MANAGEMENT_POS_LEVEL_NAME;
	private String ACCUSER_MANAGEMENT_DEPT_CODE;
	private String ACCUSER_MANAGEMENT_DEPT_NAME;
	private int ACCUSER_MANAGEMENT_DEPT_LEVEL;
	private String ACCUSER_MANAGEMENT_UNDER_DEPT_CODE;
	private String ACCUSER_MANAGEMENT_UNDER_DEPT_NAME;
	private int ACCUSER_MANAGEMENT_UNDER_DEPT_LEVEL;
	private String ACCUSER_MANAGEMENT_WORK_DEPT_CODE;
	private String ACCUSER_MANAGEMENT_WORK_DEPT_NAME;
	private int ACCUSER_MANAGEMENT_WORK_DEPT_LEVEL;
	private String ACCUSER_MANAGEMENT_OFFICE_CODE;
	private String ACCUSER_MANAGEMENT_OFFICE_NAME;
	private String ACCUSER_MANAGEMENT_OFFICE_SHORT_NAME;
	private String ACCUSER_REPRESENT_POS_CODE;
	private String ACCUSER_REPRESENT_POS_NAME;
	private String ACCUSER_REPRESENT_POS_LEVEL;
	private String ACCUSER_REPRESENT_POS_LEVEL_NAME;
	private String ACCUSER_REPRESENT_DEPT_CODE;
	private String ACCUSER_REPRESENT_DEPT_NAME;
	private int ACCUSER_REPRESENT_DEPT_LEVEL;
	private String ACCUSER_REPRESENT_UNDER_DEPT_CODE;
	private String ACCUSER_REPRESENT_UNDER_DEPT_NAME;
	private int ACCUSER_REPRESENT_UNDER_DEPT_LEVEL;
	private String ACCUSER_REPRESENT_WORK_DEPT_CODE;
	private String ACCUSER_REPRESENT_WORK_DEPT_NAME;
	private int ACCUSER_REPRESENT_WORK_DEPT_LEVEL;
	private String ACCUSER_REPRESENT_OFFICE_CODE;
	private String ACCUSER_REPRESENT_OFFICE_NAME;
	private String ACCUSER_REPRESENT_OFFICE_SHORT_NAME;
	private String SUB_DISTRICT_NAME_TH;
	private String SUB_DISTRICT_NAME_EN;
	private String DISTRICT_NAME_TH;
	private String DISTRICT_NAME_EN;
	private String PROVINCE_NAME_TH;
	private String PROVINCE_NAME_EN;
	private int LAWSUIT_NO;
	private String LAWSUIT_NO_YEAR; //
	private int LAWSUIT_IS_OUTSIDE;
	private String LAWSUIT_DATE; //
	private int PROVE_NO;
	private String PROVE_NO_YEAR; //
	private int PROVE_IS_OUTSIDE;
	private String RECEIVE_DOC_DATE; //
	private String GUILTBASE_NAME;
    private String FINE;
	private int SUBSECTION_RULE_ID;
	private int FINE_TYPE;
	private int SUBSECTION_ID;
	private String SUBSECTION_NAME;
	private String SUBSECTION_DESC;
	private int SECTION_ID;
	private String SECTION_NAME;
	private String PENALTY_DESC;
	private String FINE_RATE_MIN;
	private String FINE_RATE_MAX;
	private String FINE_MIN;
	private String FINE_MAX;
	private List<CompareArrestIndictmentProduct> CompareArrestIndictmentProduct;
	private List<CompareArrestIndictmentDetail> CompareArrestIndictmentDetail;
	private List<CompareProveProduct> CompareProveProduct;
	private List<CompareGuiltbaseFine> CompareGuiltbaseFine;

}
