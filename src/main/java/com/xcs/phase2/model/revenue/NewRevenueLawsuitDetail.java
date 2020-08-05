package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class NewRevenueLawsuitDetail extends RevenueModel {

    private int LAWSUIT_DETAIL_ID;
    private int LAWSUIT_ID;
    private int INDICTMENT_DETAIL_ID;
    private int COURT_ID;
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
    private float FINE;
    private int IS_PAYONCE;
    private String FINE_DATE;
    private String PAYMENT_PERIOD;
    private String PAYMENT_DATE;
    private String UNJUDGEMENT_NO;
    private String UNJUDGEMENT_NO_YEAR;
    private int ARREST_ID;
    private int LAWBREAKER_ID;
    private String LAWBREAKER_NAME;
    private int PERSON_TYPE;
    private int ENTITY_TYPE;
    private int MISTREAT_NO;
    private List<NewRevenuePayment> Payment;
    private List<NewRevenueNotice> Notice;


}
