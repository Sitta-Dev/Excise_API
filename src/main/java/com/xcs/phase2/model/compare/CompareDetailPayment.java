package com.xcs.phase2.model.compare;

import lombok.Data;

@Data
public class CompareDetailPayment extends CompareModel {

	private int PAYMENT_ID;
	private int COMPARE_DETAIL_ID;
	private int PAYMENT_TYPE;
	private float PAYMENT_FINE;
	private String REFFERENCE_NO;
	private int IS_ACTIVE;

}
