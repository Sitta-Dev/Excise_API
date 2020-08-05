package com.xcs.phase2.request.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutListgetByKeywordReq extends EvidenceOutRequest {

	private String TEXT_SEARCH;
	private String[] EVIDENCE_OUT_TYPE;
	private String OPERATION_OFFICE_CODE;





}
