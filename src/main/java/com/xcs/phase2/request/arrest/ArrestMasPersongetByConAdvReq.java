package com.xcs.phase2.request.arrest;

import lombok.Data;

@Data
public class ArrestMasPersongetByConAdvReq extends ArrestRequest {

	private String PERSON_TYPE;
	private String ENTITY_TYPE;

	private String ID_CARD;
	private String PASSPORT_NO;
	private String COMPANY_REGISTRATION_NO;

	private String PERSON_NAME;
	private String COMPANY_NAME;
	private String COUNTRY_NAME;
	private String RELATIONSHIP_NAME;

	private String FATHER_NAME;
	private String MOTHER_NAME;
	private String EXCISE_REGISTRATION_NO;



}
