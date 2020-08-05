package com.xcs.phase2.model.compare;

import lombok.Data;

@Data
public class ComparePaymentDetail extends CompareModel {

	private int PAYMENT_DETAIL_ID;
	private int PAYMENT_ID;
	private int NOTICE_ID;
	private int IS_REQUEST_BRIBE;
	private int IS_ACTIVE;

}
