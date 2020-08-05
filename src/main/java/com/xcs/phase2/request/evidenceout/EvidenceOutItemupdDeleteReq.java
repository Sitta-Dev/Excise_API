package com.xcs.phase2.request.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutItemupdDeleteReq extends EvidenceOutRequest{

	private int EVIDENCE_OUT_ITEM_ID;
	private int STOCK_ID;
	private float BALANCE_QTY;
}
