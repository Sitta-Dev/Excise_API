package com.xcs.phase2.model.lawsult;

import lombok.Data;

@Data
public class LawsuitPaymentDetail extends LawsultModel {

	private int PAYMENT_DETAIL_ID;
	private int PAYMENT_ID;
	private int NOTICE_ID;
	private int IS_REQUEST_BRIBE;
	private int IS_ACTIVE;

}
