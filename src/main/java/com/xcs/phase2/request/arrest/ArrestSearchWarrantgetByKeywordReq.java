package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestSearchWarrantgetByKeywordReq extends ArrestRequest {

	private String TEXT_SEARCH;
	private String ACCOUNT_OFFICE_CODE;
}
