package com.xcs.phase2.model.arrest;

import java.util.List;

import lombok.Data;

@Data
public class ArrestIndictment extends ArrestModel {

	private int INDICTMENT_ID;
	private int ARREST_ID;
	private int GUILTBASE_ID;
	private float FINE_ESTIMATE;
	private int IS_LAWSUIT_COMPLETE;
	private int IS_ACTIVE;
	
	private String GUILTBASE_NAME;
	private String FINE;
	private int IS_PROVE;
	private int IS_COMPARE;
	private String SUBSECTION_NAME;
	private String SUBSECTION_DESC;
	private String SECTION_NAME;
	private String PENALTY_DESC;


	private List<ArrestIndictmentDetail> ArrestIndictmentDetail;

}
