package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductCategoryRDBgetByConReq extends MasterRequest {

    private String GROUP_PRODUCT_CODE;
    private String GROUP_PRC_PRODUCT_CODE;
    private String RDB_PRODUCT_CODE;
    private String CATEGORY_GROUP_CODE;

}
