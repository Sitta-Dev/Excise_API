package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class LawbreakerRelationshipgetByPersonIdReq extends ArrestRequest {

	private String ARREST_CODE;
}
