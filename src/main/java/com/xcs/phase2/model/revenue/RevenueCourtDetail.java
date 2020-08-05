package com.xcs.phase2.model.revenue;

import lombok.Data;

@Data
public class RevenueCourtDetail extends RevenueModel {

    private int LAWSUIT_ID;
    private int INDICTMENT_ID;
    private int OFFICE_ID;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private int IS_LAWSUIT;
    private String REMARK_NOT_LAWSUIT;
    private String LAWSUIT_NO;
    private String LAWSUIT_NO_YEAR;
    private String LAWSUIT_DATE;
    private String TESTIMONY;
    private String DELIVERY_DOC_NO_1;
    private String DELIVERY_DOC_NO_2;
    private String DELIVERY_DOC_DATE;
    private int IS_OUTSIDE;
    private int IS_SEIZE;

    private int LAWSUIT_DETAIL_ID;
    private int LAWSUIT_ID_1;
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
    private String IMPRISON_TIME_UNIT;
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
    private String IS_DISMISS;
    private String UNJUDGEMENT_NO;
    private String UNJUDGEMENT_NO_YEAR;

    private int LAWBREAKER_ID;
    private int ARREST_ID;
    private int PERSON_ID;
    private int TITLE_ID;
    private int PERSON_TYPE;
    private int ENTITY_TYPE;
    private String TITLE_NAME_TH;
    private String TITLE_NAME_EN;
    private String TITLE_SHORT_NAME_TH;
    private String TITLE_SHORT_NAME_EN;
    private String FIRST_NAME;
    private String MIDDLE_NAME;
    private String LAST_NAME;
    private String OTHER_NAME;
    private String COMPANY_NAME;
    private String COMPANY_REGISTRATION_NO;
    private String EXCISE_REGISTRATION_NO;
    private String ID_CARD;
    private int AGE;
    private String PASSPORT_NO;
    private String CAREER;
    private String PERSON_DESC;
    private String EMAIL;
    private String TEL_NO;
    private int MISTREAT_NO;


}
