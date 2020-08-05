package com.xcs.phase2.model.compare;

import lombok.Data;

@Data
public class CompareArrestIndictmentProduct extends CompareModel {

	private int PRODUCT_INDICTMENT_ID;
	private int PRODUCT_ID;
	private int INDICTMENT_ID;
	private float SIZES;
	private String SIZES_UNIT;
	private float QUANTITY;
	private String QUANTITY_UNIT;
	private float VOLUMN;
	private String VOLUMN_UNIT;
	private String PRODUCT_GROUP_NAME;
	private String PRODUCT_CATEGORY_NAME;
	private String PRODUCT_TYPE_NAME;
	private String PRODUCT_SUBTYPE_NAME;
	private String PRODUCT_SUBSETTYPE_NAME;
	private String PRODUCT_BRAND_NAME_TH;
	private String PRODUCT_BRAND_NAME_EN;
	private String PRODUCT_SUBBRAND_NAME_TH;
	private String PRODUCT_SUBBRAND_NAME_EN;
	private String PRODUCT_MODEL_NAME_TH;
	private String PRODUCT_MODEL_NAME_EN;

	private String PRODUCT_CODE;
	private String PRODUCT_GROUP_ID;
	private String PRODUCT_GROUP_CODE;
	private String PRODUCT_CATEGORY_ID;
	private String PRODUCT_CATEGORY_CODE;
	private String PRODUCT_DESC;


}
