package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class ProductMapping extends MasterProductModel {

    private int PRODUCT_MAPPING_ID;
    private String PRODUCT_CODE;
    private String PRODUCT_REF_CODE;
    private int PRODUCT_GROUP_ID;
    private String PRODUCT_GROUP_NAME;
    private int PRODUCT_CATEGORY_ID;
    private String PRODUCT_CATEGORY_NAME;
    private int PRODUCT_TYPE_ID;
    private String PRODUCT_TYPE_NAME;
    private int PRODUCT_SUBTYPE_ID;
    private String PRODUCT_SUBTYPE_NAME;
    private int PRODUCT_SUBSETTYPE_ID;
    private String PRODUCT_SUBSETTYPE_NAME;
    private int PRODUCT_BRAND_ID;
    private String PRODUCT_BRAND_NAME_TH;
    private String PRODUCT_BRAND_NAME_EN;
    private int PRODUCT_SUBBRAND_ID;
    private String PRODUCT_SUBBRAND_NAME_TH;
    private String PRODUCT_SUBBRAND_NAME_EN;
    private int PRODUCT_MODEL_ID;
    private String PRODUCT_MODEL_NAME_TH;
    private String PRODUCT_MODEL_NAME_EN;
    private int PRODUCT_TAXDETAIL_ID;
    private String TAX_VALUE;
    private String TAX_VOLUMN;
    private String TAX_VOLUMN_UNIT;
    private int UNIT_ID;
    private float SUGAR;
    private float CO2;
    private float DEGREE;
    private float PRICE;
    private float SIZES;
    private String SIZES_UNIT;
    private int IS_DOMESTIC;
    private int IS_ACTIVE;
    private String CREATE_DATE;
    private String UPDATE_DATE;
    private long UPDATE_USER_ACCOUNT_ID;
    private String QUANTITY_UNIT;
    private String LAW_DUTY_CODE;
    private String EXPIRE_DATE;
    private String PRODUCT_NAME_DESC;

    private int IS_TAX_VALUE;
    private int IS_TAX_VOLUMN;



}
