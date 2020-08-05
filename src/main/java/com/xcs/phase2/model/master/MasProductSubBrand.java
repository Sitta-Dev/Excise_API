package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductSubBrand extends MasterProductModel {

    private int PRODUCT_SUBBRAND_ID;
    private String PRODUCT_SUBBRAND_CODE;
    private String PRODUCT_SUBBRAND_NAME_TH;
    private String PRODUCT_SUBBRAND_NAME_EN;
    private int IS_ACTIVE;
    private String CREATE_DATE;
    private long CREATE_USER_ACCOUNT_ID;
    private String UPDATE_DATE;
    private long UPDATE_USER_ACCOUNT_ID;
    private String PRODUCT_GROUP_CODE;
    private String PRODUCT_BRAND_CODE;
    private String PRODUCT_CATEGORY_CODE;

}
