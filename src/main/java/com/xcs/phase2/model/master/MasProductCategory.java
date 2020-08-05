package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductCategory extends MasterProductModel {

    private int PRODUCT_CATEGORY_ID;
    private int PRODUCT_GROUP_ID;
    private String PRODUCT_CATEGORY_CODE;
    private String PRODUCT_CATEGORY_NAME;
    private int IS_TAX;
    private int IS_ACTIVE;
    private String CREATE_DATE;
    private long CREATE_USER_ACCOUNT_ID;
    private String UPDATE_DATE;
    private long UPDATE_USER_ACCOUNT_ID;

}
