package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductCategorygetByConReq extends MasterRequest {

    private String TEXT_SEARCH;
    private String PRODUCT_CATEGORY_CODE;
    private String PRODUCT_GROUP_ID;
    private String PRODUCT_CATEGORY_ID;



}
