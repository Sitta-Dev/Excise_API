package com.xcs.phase2.model.arrest;

import lombok.Data;

@Data
public class ArrestMasPersonRelationship extends ArrestModel {

	private int PERSON_RELATIONSHIP_ID;
	private int RELATIONSHIP_ID;
	private int PERSON_ID;
	//private String RELATIONSHIP_NAME_TH;
	private String RELATIONSHIP_NAME;
	private String TITLE_NAME_TH;
	private String TITLE_NAME_EN;
	private String TITLE_SHORT_NAME_TH;
	private String TITLE_SHORT_NAME_EN;
	private String FIRST_NAME;
	private String MIDDLE_NAME;
	private String LAST_NAME;
	private String OTHER_NAME;

}
