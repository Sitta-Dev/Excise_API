package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductBrand extends MasterProductModel {

    private int PRODUCT_BRAND_ID;
    private String PRODUCT_BRAND_CODE;
    private String PRODUCT_BRAND_NAME_TH;
    private String PRODUCT_BRAND_NAME_EN;
    private int IS_ACTIVE;
    private String PRODUCT_GROUP_CODE;
    private String PRODUCT_CATEGORY_CODE;


}
