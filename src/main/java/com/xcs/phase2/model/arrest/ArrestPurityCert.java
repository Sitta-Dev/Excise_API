package com.xcs.phase2.model.arrest;

import lombok.Data;

@Data
public class ArrestPurityCert extends ArrestModel {

	private int PURITYCERT_ID;
	private int ARREST_ID;
	private String PURITYCERT_CODE;
	private String PURITYCERT_DATE;
	private String OFFICE_NAME;
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

}
