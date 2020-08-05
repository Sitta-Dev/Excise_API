package com.xcs.phase2.request.lawsult;

import lombok.Data;

@Data
public class LawsuiltListgetByConAdvReq extends LawsuitRequest {

	private String ARREST_CODE;
	private String OCCURRENCE_DATE_FROM;
	private String OCCURRENCE_DATE_TO;
	private String ARREST_STAFF;
	private String LAWBREAKER_STAFF;
	private String SUBSECTION_NAME;
	private String GUILTBASE_NAME;
	private String LAWSUIT_NO;
	private String LAWSUIT_NO_YEAR;
	private String IS_OUTSIDE;
	private String LAWSUILT_DATE_START_FROM;
	private String LAWSUILT_DATE_START_TO;
	private String LAWSUILT_STAFF;
	private String LAWSUILT_OFFICE_NAME;
	private String IS_LAWSUIT_COMPLETE;
	private String ARREST_LOCAL;
	private String ACCOUNT_OFFICE_CODE;
	private String LAWSUIT_TYPE;





}
