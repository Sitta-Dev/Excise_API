package com.xcs.phase2.model.compare;

import lombok.Data;

import java.util.List;

@Data
public class Compare extends CompareModel {

	private int COMPARE_ID;
	private int LAWSUIT_ID;
	private int OFFICE_ID;
	private float TREASURY_RATE;
	private float BRIBE_RATE;
	private float REWARD_RATE;
	private String OFFICE_CODE;
	private String OFFICE_NAME;
	private int COMPARE_NO;
	private String COMPARE_NO_YEAR; //
	private String COMPARE_DATE; //
	private int IS_OUTSIDE;
	private int IS_ACTIVE;
	private String CREATE_DATE;
	private int CREATE_USER_ACCOUNT_ID;
	private String UPDATE_DATE;
	private int UPDATE_USER_ACCOUNT_ID;
	private List<CompareMapping> CompareMapping;


}
