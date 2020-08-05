package com.xcs.phase2.request.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutListgetByConAdvReq extends EvidenceOutRequest {


	private String EVIDENCE_OUT_CODE;
	private String EVIDENCE_OUT_DATE_FROM;
	private String EVIDENCE_OUT_DATE_TO;
	private String EVIDENCE_OUT_NO;
	private String EVIDENCE_OUT_APPROVE_DATE_FROM;
	private String EVIDENCE_OUT_APPROVE_DATE_TO;
	private String STAFF_NAME;
	private String STAFF_OFFICE_CODE;
	private String[] EVIDENCE_OUT_TYPE;
	private String OPERATION_OFFICE_CODE;

}
