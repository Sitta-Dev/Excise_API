package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductSize extends MasterProductModel {

    private int SIZE_ID;
    private String SIZE_DESC;
    private String SIZE_NAME_EN;
    private String SIZE_SHORT_NAME;
    private String CREATE_DATE;
    private long CREATE_USER_ACCOUNT_ID;
    private String UPDATE_DATE;
    private String EXPIRED_DATE;
    private int IS_ACTIVE;
    private String PRODUCT_GROUP_CODE;
    private String UNIT_CODE;
    private String SIZE_CODE;
    private String PRODUCT_CATEGORY_CODE;

}
