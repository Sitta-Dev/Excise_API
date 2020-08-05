package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestNoticegetByConAdvReq extends ArrestRequest {

	private String NOTICE_CODE;
	private String NOTICE_DATE_FROM;
	private String NOTICE_DATE_TO;
	private String STAFF_NAME;
	private String OFFICE_NAME;
	//private String SUSPECT_NAME;
	private String ACCOUNT_OFFICE_CODE;

}
