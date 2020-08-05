package com.xcs.phase2.model.lawsult;

import lombok.Data;

@Data
public class LawsuitLawbreaker extends LawsultModel {

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
	private int IS_ACTIVE;
	private String COMPANY_NAME;

//	private int LAWSUIT_TYPE;
//	private int LAWSUIT_END;

}
