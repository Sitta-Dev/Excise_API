package com.xcs.phase2.request.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutStockBalancegetByLawsuitNoReq extends EvidenceOutRequest {

	private String LAWSUIT_NO;
	private String LAWSUIT_NO_YEAR;
	private String IS_OUTSIDE;
	private String LAWSUIT_DATE_FROM;
	private String LAWSUIT_DATE_TO;
	private String DELIVERY_NO;
	private String DELIVERY_DATE_FROM;
	private String DELIVERY_DATE_TO;
	private String EVIDENCE_IN_DATE_FROM;
	private String EVIDENCE_IN_DATE_TO;





}
