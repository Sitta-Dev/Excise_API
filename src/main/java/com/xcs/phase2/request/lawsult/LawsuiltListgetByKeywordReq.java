package com.xcs.phase2.request.lawsult;



import lombok.Data;
@Data
public class LawsuiltListgetByKeywordReq extends LawsuitRequest {

	private String TEXT_SEARCH;
	private String ACCOUNT_OFFICE_CODE;
}
