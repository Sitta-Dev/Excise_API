package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestMasGuiltbasegetByKeywordReq extends ArrestRequest {

	private String TEXT_SEARCH;
}
