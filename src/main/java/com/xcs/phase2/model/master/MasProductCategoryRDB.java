package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductCategoryRDB extends MasterProductModel {

    private String PRODUCT_CODE;
    private String CATEGORY_GROUP_CODE;
    private String CATEGORY_GROUP_DESC;
    private String CATEGORY_CODE;
    private String CATEGORY_DESC;

}


