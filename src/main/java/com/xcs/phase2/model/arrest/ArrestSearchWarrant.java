package com.xcs.phase2.model.arrest;

import lombok.Data;

@Data
public class ArrestSearchWarrant extends ArrestModel {

	private int SEARCH_WARRANT_ID;
	private int ARREST_ID;
	private String REQUEST_CODE;
	private String REQUEST_NO;
	private String REQUEST_NO_YEAR;
	private String REQUEST_DATE;
	private String STAFF_TITLE_NAME_TH;
	private String STAFF_TITLE_NAME_EN;
	private String STAFF_TITLE_SHORT_NAME_TH;
	private String STAFF_TITLE_SHORT_NAME_EN;
	private String STAFF_FIRST_NAME;
	private String STAFF_LAST_NAME;
	private String STAFF_OPERATION_OFFICE_NAME;
	private String STAFF_OPERATION_OFFICE_SHORT_NAME;
	private String STAFF_OPREATION_POS_NAME;
	private String STAFF_MANAGEMENT_OFFICE_NAME;
	private String STAFF_MANAGEMENT_OFFICE_SHORT_NAME;
	private String STAFF_MANAGEMENT_POS_NAME;
	private String STAFF_REPRESENT_OFFICE_NAME;
	private String STAFF_REPRESENT_OFFICE_SHORT_NAME;
	private String STAFF_REPRESENT_POS_NAME;
	private String REQUEST_DATE_FROM;
	private String PRESENT_COURT_NAME;
	private String JUDGE_TITLE_NAME_TH;
	private String JUDGE_TITLE_NAME_EN;
	private String JUDGE_TITLE_SHORT_NAME_TH;
	private String JUDGE_TITLE_SHORT_NAME_EN;
	private String JUDGE_FIRST_NAME;
	private String JUDGE_LAST_NAME;
	private String PERSON_TITLE_NAME_TH;
	private String PERSON_TITLE_NAME_EN;
	private String PERSON_TITLE_SHORT_NAME_TH;
	private String PERSON_TITLE_SHORT_NAME_EN;
	private String PERSON_FIRST_NAME;
	private String PERSON_MIDDLE_NAME;
	private String PERSON_LAST_NAME;
	private String PERSON_OTHER_NAME;
	private String SUB_DISTRICT_NAME_TH;
	private String SUB_DISTRICT_NAME_EN;
	private String DISTRICT_NAME_TH;
	private String DISTRICT_NAME_EN;
	private String PROVINCE_NAME_TH;
	private String PROVINCE_NAME_EN;
	private int SEARCH_WARRANT_NO;
	private String SEARCH_WARRANT_NO_YEAR;
	private String SEARCH_WARRANT_DATE;
	private int CONSIDER_UNDECIDE_NO;
	private String CONSIDER_UNDECIDE_NO_YEAR;
	private int CONSIDER_DECIDE_NO;
	private String CONSIDER_DECIDE_NO_YEAR;
	private String CONSIDER_DATE;

	private String OPERATION_OFFICE_CODE;
	private String MANAGEMENT_OFFICE_CODE;
	private String REPRESENT_OFFICE_CODE;

}
