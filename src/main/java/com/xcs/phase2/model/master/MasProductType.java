package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductType extends MasterProductModel {
	
	private int PRODUCT_TYPE_ID;
	private int PRODUCT_CATEGORY_ID;
	private String PRODUCT_TYPE_CODE;
	private String PRODUCT_TYPE_NAME;
	private int IS_TAX;
	private int IS_ACTIVE;
}
