package com.xcs.phase2.model.lawsult;

import lombok.Data;

import java.util.List;

@Data
public class LawsuitDetail extends LawsultModel {

	private int LAWSUIT_DETAIL_ID;
	private int LAWSUIT_ID;
	private int INDICTMENT_DETAIL_ID;
	private int COURT_ID;
	private int LAWSUIT_TYPE;
	private int LAWSUIT_END;
	private String COURT_NAME;
	private int UNDECIDE_NO_1;
	private String UNDECIDE_NO_YEAR_1;
	private int DECIDE_NO_1;
	private String DECIDE_NO_YEAR_1;
	private int UNDECIDE_NO_2;
	private String UNDECIDE_NO_YEAR_2;
	private int DECIDE_NO_2;
	private String DECIDE_NO_YEAR_2;
	private int JUDGEMENT_NO;
	private String JUDGEMENT_NO_YEAR;
	private String JUDGEMENT_DATE;
	private int IS_IMPRISON;
	private String IMPRISON_TIME;
	private int IMPRISON_TIME_UNIT;
	private int IS_FINE;
	private float FINE;
	private int IS_PAYONCE;
	private String FINE_DATE;
	private int PAYMENT_PERIOD;
	private int PAYMENT_PERIOD_DUE;
	private int PAYMENT_PERIOD_DUE_UNIT;
	private int PAYMENT_CHANNEL;
	private int PAYMENT_BANK;
	private String PAYMENT_REF_NO;
	private String PAYMENT_DATE;
	private int IS_DISMISS;
	private int IS_ACTIVE;
	private String UNJUDGEMENT_NO;
	private String UNJUDGEMENT_NO_YEAR;
	private List<LawsuitPayment> LawsuitPayment;

}
