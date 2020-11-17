package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductGroup extends MasterProductModel {

    private int PRODUCT_GROUP_ID;
    private String PRODUCT_GROUP_CODE;
    private String PRODUCT_GROUP_NAME;
    private String PRODUCT_GROUP_CODE_OLD1;
    private String PRODUCT_GROUP_CODE_OLD2;
    private String PRODUCT_GROUP_CODE_OLD3;
    private int IS_ACTIVE;

}
