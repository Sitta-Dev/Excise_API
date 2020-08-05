package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductCategoryGroup extends MasterProductModel {

    private String CATEGORY_GROUP;
    private String CATEGORY_GROUP_DESC;
    private String PRODUCT_CODE;
    private int IS_ACTIVE;

}
