package com.xcs.phase2.model.arrest;

import lombok.Data;

import java.util.List;

@Data
public class ArrestIndictmentDetailByCon extends ArrestModel {

	private int INDICTMENT_DETAIL_ID;
	private int INDICTMENT_ID;
	private int LAWBREAKER_ID;
	private int IS_ACTIVE;
	private float FINE_ESTIMATE;

//	private String TITLE_NAME_TH;
//	private String TITLE_NAME_EN;
//	private String TITLE_SHORT_NAME_TH;
//	private String TITLE_SHORT_NAME_EN;
//	private String FIRST_NAME;
//	private String MIDDLE_NAME;
//	private String LAST_NAME;
//	private String OTHER_NAME;


}
