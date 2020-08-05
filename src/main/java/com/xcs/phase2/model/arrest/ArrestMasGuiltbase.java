package com.xcs.phase2.model.arrest;

import lombok.Data;

@Data
public class ArrestMasGuiltbase extends ArrestModel {

	private int GUILTBASE_ID;
	private int SUBSECTION_RULE_ID;
	private String GUILTBASE_NAME;
	private String FINE;
	private int IS_PROVE;
	private int IS_COMPARE;

	private int SECTION_ID;
	private String SUBSECTION_NAME;
	private String SUBSECTION_DESC;
	private String SECTION_NAME;
	private String SECTION_DESC_1;
	private String PENALTY_DESC;

	private String REMARK;
	private int IS_ACTIVE;

}
