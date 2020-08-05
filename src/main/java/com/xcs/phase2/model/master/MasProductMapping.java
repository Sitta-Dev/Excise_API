package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductMapping extends MasterProductModel {

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
    private long CREATE_USER_ACCOUNT_ID;
    private String UPDATE_DATE;
    private long UPDATE_USER_ACCOUNT_ID;
    private String CATEGORY_GROUP_CODE;
    private String CATEGORY_CODE;
    private String QUANTITY_UNIT;
    private String EXPIRE_DATE;
    private String LAW_DUTY_CODE;
    private String PRODUCT_NAME_DESC;


}
