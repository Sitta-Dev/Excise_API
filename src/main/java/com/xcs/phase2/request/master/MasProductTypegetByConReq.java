package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductTypegetByConReq extends MasterRequest {

    private String TEXT_SEARCH;
    private int PRODUCT_CATEGORY_ID;
    private int PRODUCT_TYPE_ID;



}
