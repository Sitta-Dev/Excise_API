package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasStaffgetByConAdvReq extends MasterRequest{
	private String STAFF_TYPE;
	private String STAFF_CODE;
	private String ID_CARD;
	private String STAFF_NAME;
//	private String TITLE_NAME_TH;
//	private String TITLE_NAME_EN;
//	private String TITLE_SHORT_NAME_TH;
//	private String TITLE_SHORT_NAME_EN;
//	private String FIRST_NAME;
//	private String LAST_NAME;
	private String OFFICE_NAME;
//	private String OPERATION_OFFICE_NAME;
//	private String OPERATION_OFFICE_SHORT_NAME;
//	private String OPREATION_POS_NAME;
//	private String OPERATION_DEPT_NAME;
//	private String MANAGEMENT_POS_NAME;
//	private String MANAGEMENT_OFFICE_NAME;
//	private String MANAGEMENT_OFFICE_SHORT_NAME;
//	private String REPRESENT_POS_NAME;
//	private String REPRESENT_OFFICE_NAME;
	private int STATUS;

}
