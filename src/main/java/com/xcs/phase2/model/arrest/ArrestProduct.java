package com.xcs.phase2.model.arrest;

import java.util.List;

import lombok.Data;

@Data
public class ArrestProduct extends ArrestModel {

	private int PRODUCT_ID;
	private int ARREST_ID;
	private int PRODUCT_MAPPING_ID;
	private String PRODUCT_CODE;
	private String PRODUCT_REF_CODE;
	private int PRODUCT_GROUP_ID;
	private int PRODUCT_CATEGORY_ID;
	private int PRODUCT_TYPE_ID;
	private int PRODUCT_SUBTYPE_ID;
	private int PRODUCT_SUBSETTYPE_ID;
	private int PRODUCT_BRAND_ID;
	private int PRODUCT_SUBBRAND_ID;
	private int PRODUCT_MODEL_ID;
	private int PRODUCT_TAXDETAIL_ID;
	private int SIZES_UNIT_ID;
	private int QUATITY_UNIT_ID;
	private int VOLUMN_UNIT_ID;
	private String PRODUCT_GROUP_CODE;
	private String PRODUCT_GROUP_NAME;
	private String PRODUCT_CATEGORY_CODE;
	private String PRODUCT_CATEGORY_NAME;
	private String PRODUCT_TYPE_CODE;
	private String PRODUCT_TYPE_NAME;
	private String PRODUCT_SUBTYPE_CODE;
	private String PRODUCT_SUBTYPE_NAME;
	private String PRODUCT_SUBSETTYPE_CODE;
	private String PRODUCT_SUBSETTYPE_NAME;
	private String PRODUCT_BRAND_CODE;
	private String PRODUCT_BRAND_NAME_TH;
	private String PRODUCT_BRAND_NAME_EN;
	private String PRODUCT_SUBBRAND_CODE;
	private String PRODUCT_SUBBRAND_NAME_TH;
	private String PRODUCT_SUBBRAND_NAME_EN;
	private String PRODUCT_MODEL_CODE;
	private String PRODUCT_MODEL_NAME_TH;
	private String PRODUCT_MODEL_NAME_EN;
	private int IS_TAX_VALUE;
	private float TAX_VALUE;
	private int IS_TAX_VOLUMN;
	private float TAX_VOLUMN;
	private String TAX_VOLUMN_UNIT;
	private String LICENSE_PLATE;
	private String ENGINE_NO;
	private String CHASSIS_NO;
	private String PRODUCT_DESC;
	private float SUGAR;
	private float CO2;
	private float DEGREE;
	private float PRICE;
	private float SIZES;
	private String SIZES_UNIT;
	private float QUANTITY;
	private String QUANTITY_UNIT;
	private float VOLUMN;
	private String VOLUMN_UNIT;
	private String REMARK;
	private int IS_DOMESTIC;
	private int IS_ILLEGAL;
	private int IS_ACTIVE;
	private List<ArrestProductMapping> ArrestProductMapping;
}
