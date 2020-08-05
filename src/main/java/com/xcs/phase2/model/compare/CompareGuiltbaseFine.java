package com.xcs.phase2.model.compare;

import lombok.Data;

@Data
public class CompareGuiltbaseFine extends CompareModel {

	private int FINE_ID;
	private int SUBSECTION_RULE_ID;
	private int PRODUCT_GROUP_ID;
	private int MISTREAT_START_NO;
	private int MISTREAT_TO_NO;
	private int IS_FINE;
	private float FINE_RATE;
	private String MISTREAT_DESC;
	private float MISTREAT_START_VOLUMN;
	private float MISTREAT_TO_VOLUMN;
	private float FINE_AMOUNT;
	private float FINE_TAX;
	private int IS_ACTIVE;

}
