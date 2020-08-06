package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductSubType extends MasterProductModel {
	private int PRODUCT_SUBTYPE_ID;
	private int PRODUCT_TYPE_ID;
	private String PRODUCT_SUBTYPE_CODE;
	private String PRODUCT_SUBTYPE_NAME;
	private int IS_TAX;
	private int IS_ACTIVE;
	
}
