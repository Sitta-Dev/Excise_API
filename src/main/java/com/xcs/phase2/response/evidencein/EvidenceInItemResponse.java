package com.xcs.phase2.response.evidencein;

import lombok.Data;

import java.util.List;

@Data
public class EvidenceInItemResponse {

	private int EVIDENCE_IN_ITEM_ID;
	private List<EvidenceInStockBalanceResponse> EvidenceInStockBalance;
}
