package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestPurityCertgetByConAdvReq extends ArrestRequest {

	private String PURITYCERT_CODE;
	private String PURITYCERT_DATE_FROM;
	private String PURITYCERT_DATE_TO;
	private String OFFICE_NAME;
	private String STAFF_NAME;
	private String PERSON_NAME;
	private String ACCOUNT_OFFICE_CODE;

}
