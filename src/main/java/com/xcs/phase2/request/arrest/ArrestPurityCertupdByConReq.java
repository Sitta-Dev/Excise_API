package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestPurityCertupdByConReq extends ArrestRequest {

	private int PURITYCERT_ID;
	private int ARREST_ID;
}
