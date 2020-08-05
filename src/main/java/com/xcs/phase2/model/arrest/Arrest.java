package com.xcs.phase2.model.arrest;

import java.util.List;

import lombok.Data;

@Data
public class Arrest extends ArrestModel {

	private int ARREST_ID;
	private int OFFICE_ID;
	private String ARREST_CODE;
	private String OFFICE_CODE;
	private String OFFICE_NAME;
	private String ARREST_DATE;
	private String OCCURRENCE_DATE;
	private String BEHAVIOR_1;
	private String BEHAVIOR_2;
	private String BEHAVIOR_3;
	private String BEHAVIOR_4;
	private String BEHAVIOR_5;
	private String TESTIMONY;
	private int IS_REQUEST;
	private String REQUEST_DESC;
	private int IS_LAWSUIT_COMPLETE;
	private int IS_ACTIVE;
	private String CREATE_DATE;
	private int CREATE_USER_ACCOUNT_ID;
	private String UPDATE_DATE;
	private int UPDATE_USER_ACCOUNT_ID;
	private List<ArrestStaff> ArrestStaff;
	private List<ArrestLocale> ArrestLocale;
	private List<ArrestLawbreaker> ArrestLawbreaker;
	private List<ArrestProduct> ArrestProduct;
	private List<ArrestIndictment> ArrestIndictment;
	
	private List<ArrestNotice> ArrestNotice;
	private List<ArrestPurityCert> ArrestPurityCert;
	private List<ArrestSearchWarrant> ArrestSearchWarrant;


}
