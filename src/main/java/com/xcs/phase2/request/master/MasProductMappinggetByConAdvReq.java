package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductMappinggetByConAdvReq extends MasterRequest {

    private String PRODUCT_GROUP_ID;
    private String PRODUCT_CODE;
    private String PRODUCT_NAME_DESC;
    
    private String PRODUCT_CATEGORY_ID;
    private String PRODUCT_TYPE_ID;
    private String SIZES;
    private String SIZES_UNIT;
    private String PRODUCT_BRAND_NAME;
    private String PRODUCT_SUBBRAND_NAME;
    private String PRODUCT_MODEL_NAME;
}
