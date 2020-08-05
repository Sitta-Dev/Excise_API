package com.xcs.phase2.model.arrest;

import lombok.Data;

@Data
public class ArrestLocale extends ArrestModel {

	private int LOCALE_ID;
	private int ARREST_ID;
	private int SUB_DISTRICT_ID;
	private String GPS;
	private String ADDRESS_NO;
	private String VILLAGE_NO;
	private String BUILDING_NAME;
	private String ROOM_NO;
	private String FLOOR;
	private String VILLAGE_NAME;
	private String ALLEY;
	private String LANE;
	private String ROAD;
	private int ADDRESS_TYPE;
	private int ADDRESS_STATUS;
	private String POLICE_STATION;
	private String LOCATION;
	private int IS_ACTIVE;
	private String SUB_DISTRICT_NAME_TH;
	private String SUB_DISTRICT_NAME_EN;
	private String DISTRICT_NAME_TH;
	private String DISTRICT_NAME_EN;
	private String PROVINCE_NAME_TH;
	private String PROVINCE_NAME_EN;

}
