package com.xcs.phase2.request.arrest;



import lombok.Data;

@Data
public class ArrestListgetByKeywordReq extends ArrestRequest {

	private String TEXT_SEARCH;
	private String ACCOUNT_OFFICE_CODE;
}
