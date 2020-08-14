package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasProductUnitMapping extends MasterProductModel {
	
    private int UNIT_ID;
    private String UNIT_NAME_TH;
    private String UNIT_NAME_EN;
    private String UNIT_SHORT_NAME;
    private int IS_ACTIVE;
    private String UNIT_CODE;
    private int UNIT_MAPPING_ID;
    private String PRODUCT_GROUP_CODE;
    private String USED_FOR;
    private String PRODUCT_CATEGORY_CODE;

}
