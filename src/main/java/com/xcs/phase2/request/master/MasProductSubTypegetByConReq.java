package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductSubTypegetByConReq extends MasterRequest {

    private String TEXT_SEARCH;
    private int PRODUCT_SUBTYPE_ID;
    private int PRODUCT_TYPE_ID;
    private String PRODUCT_SUBTYPE_CODE;
    


}
