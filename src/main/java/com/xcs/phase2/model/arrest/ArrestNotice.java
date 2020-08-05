package com.xcs.phase2.model.arrest;

import lombok.Data;

@Data
public class ArrestNotice extends ArrestModel {

	private int NOTICE_ID;
	private int ARREST_ID;
	private String NOTICE_CODE;
	private String NOTICE_DATE;
	private String OFFICE_NAME;//
	private int IS_MATCH;
	private int STAFF_ID;
	private int STAFF_REF_ID;
	private int TITLE_ID;
	private String STAFF_CODE;
	private String STAFF_TYPE;
	private String TITLE_NAME_TH;
	private String TITLE_NAME_EN;
	private String TITLE_SHORT_NAME_TH;
	private String TITLE_SHORT_NAME_EN;
	private String FIRST_NAME;
	private String LAST_NAME;
	private int STATUS;
	private String REMARK;
	private int CONTRIBUTOR_ID;



















}
