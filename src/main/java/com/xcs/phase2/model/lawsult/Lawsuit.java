package com.xcs.phase2.model.lawsult;

import java.util.List;

import lombok.Data;

@Data
public class Lawsuit extends LawsultModel {

	private int LAWSUIT_ID;
	private int INDICTMENT_ID;
	private int OFFICE_ID;
	private String OFFICE_CODE;
	private String OFFICE_NAME;
	private int IS_LAWSUIT;
	private String REMARK_NOT_LAWSUIT;
	private int LAWSUIT_NO;
	private String LAWSUIT_NO_YEAR;
	private String LAWSUIT_DATE;
	private String TESTIMONY;
	private String DELIVERY_DOC_NO_1;
	private String DELIVERY_DOC_NO_2;
	private String DELIVERY_DOC_DATE;
	private int IS_OUTSIDE;
	private int IS_SEIZE;
	private int IS_ACTIVE;
	private String CREATE_DATE;
	private int CREATE_USER_ACCOUNT_ID;
	private String UPDATE_DATE;
	private int UPDATE_USER_ACCOUNT_ID;

	private List<LawsuitStaff> LawsuitStaff;
	private List<LawsuitDetail> LawsuitDetail;

}
