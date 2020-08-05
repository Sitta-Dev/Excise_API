package com.xcs.phase2.request.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutIngetByKeywordReq extends EvidenceOutRequest{

	private int WAREHOUSE_ID;
	private int EVIDENCE_IN_TYPE;
}
