package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestSearchWarrantgetByConAdvReq extends ArrestRequest {

	private String REQUEST_CODE;
	private String REQUEST_NO;
	private String REQUEST_NO_YEAR;
	private String COURT_NAME;
	private String REQUEST_DATE_FROM;
	private String REQUEST_DATE_TO;
	private String PERSON_NAME;
	private String STAFF_NAME;
	private String CONSIDER_UNDECIDE_NO;
	private String CONSIDER_UNDECIDE_NO_YEAR;
	private String CONSIDER_DECIDE_NO;
	private String CONSIDER_DECIDE_NO_YEAR;
	private String CONSIDER_DATE_FROM;
	private String CONSIDER_DATE_TO;
	private String SEARCH_WARRANT_NO;
	private String SEARCH_WARRANT_NO_YEAR;
	private String SEARCH_WARRANT_DATE_FROM;
	private String SEARCH_WARRANT_DATE_TO;
	private String ACCOUNT_OFFICE_CODE;

}
