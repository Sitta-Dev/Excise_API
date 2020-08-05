package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestListgetByConAdvReq extends ArrestRequest {

	private String ARREST_CODE;
	private String OCCURRENCE_DATE_FROM;
	private String OCCURRENCE_DATE_TO;
	private String STAFF_NAME;
	private String LAWBREAKER_NAME;
	private String GUILTBASE_NAME;
	private String OFFICE_NAME;
	private String LOCALE_NAME;
	private Integer IS_LAWSUIT_COMPLETE;
	private String ACCOUNT_OFFICE_CODE;
	private String SUBSECTION_NAME;
	//private String COMPANY_NAME;

}
