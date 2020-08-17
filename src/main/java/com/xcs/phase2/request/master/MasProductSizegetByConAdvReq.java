package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductSizegetByConAdvReq extends MasterRequest {

    private int SIZE_ID;
    private String SIZE_DESC;
    private String PRODUCT_GROUP_CODE;
    private String UNIT_CODE;
    private String PRODUCT_CATEGORY_CODE;

}
