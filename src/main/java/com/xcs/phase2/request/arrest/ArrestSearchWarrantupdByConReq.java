package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestSearchWarrantupdByConReq extends ArrestRequest {

	private int SEARCH_WARRANT_ID;
	private int ARREST_ID;
}
