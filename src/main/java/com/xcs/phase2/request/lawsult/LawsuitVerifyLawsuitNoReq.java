package com.xcs.phase2.request.lawsult;

import lombok.Data;

@Data
public class LawsuitVerifyLawsuitNoReq extends LawsuitRequest {

	private Integer LAWSUIT_NO;
	private String LAWSUIT_NO_YEAR;
	private Integer IS_OUTSIDE;
	private String ACCOUNT_OFFICE_CODE;
	
	
	

}
