package com.xcs.phase2.model.adjust;

import lombok.Data;

@Data
public class AdjustCompareArrestIndictmentDetail extends AdjustModel {

	private int INDICTMENT_DETAIL_ID;
	private int INDICTMENT_ID;
	private int LAWBREAKER_ID;
	private int PERSON_ID;
	private String COMPANY_REGISTRATION_NO;
	private String EXCISE_REGISTRATION_NO;
	private String ID_CARD;
	private String PASSPORT_NO;
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

}
