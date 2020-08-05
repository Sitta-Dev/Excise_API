package com.xcs.phase2.request.evidenceout;

import lombok.Data;

@Data
public class EvidenceOutStockBalanceupdByConReq extends EvidenceOutRequest{

	private int STOCK_ID;
	private int BALANCE_QTY;
	private int IS_FINISH;
}
