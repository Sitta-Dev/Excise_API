package com.xcs.phase2.model.lawsult;

import java.util.List;

import lombok.Data;

@Data
public class LawsuitArrestIndictment extends LawsultModel {

	private int INDICTMENT_ID;
	private int ARREST_ID;
	private String ARREST_CODE;
	private String OCCURRENCE_DATE;
	private String ARREST_OFFICE_NAME;
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
	private String GUILTBASE_NAME;
	private String FINE;
	private int IS_PROVE;
	private int IS_COMPARE;
	private String SUBSECTION_NAME;
	private String SUBSECTION_DESC;
	private String SECTION_NAME;
	private String PENALTY_DESC;
	private List<LawsuitArrestIndictmentProduct> LawsuitArrestIndictmentProduct;
	private List<LawsuitArrestIndictmentDetail> LawsuitArrestIndictmentDetail;
	private List<LawsuitNotice> LawsuitNotice;
	private List<LawsuitLocale> LawsuitLocale;

}
