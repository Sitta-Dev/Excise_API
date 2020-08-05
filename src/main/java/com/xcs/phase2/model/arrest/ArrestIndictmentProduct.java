package com.xcs.phase2.model.arrest;

import lombok.Data;

import java.util.List;

@Data
public class ArrestIndictmentProduct extends ArrestModel {

	private int PRODUCT_INDICTMENT_ID;
	private int PRODUCT_ID;
	private int INDICTMENT_DETAIL_ID;
	private int SIZES_UNIT_ID;
	private int QUATITY_UNIT_ID;
	private int VOLUMN_UNIT_ID;
	private float SIZES;
	private String SIZES_UNIT;
	private float QUANTITY;
	private String QUANTITY_UNIT;
	private float VOLUMN;
	private String VOLUMN_UNIT;
	private float FINE_ESTIMATE;
	private int IS_ILLEGAL;
	private int IS_ACTIVE;
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
	private int PRODUCT_GROUP_ID;
	private List<ArrestIndictmentDetail> ArrestIndictmentDetail;

}
