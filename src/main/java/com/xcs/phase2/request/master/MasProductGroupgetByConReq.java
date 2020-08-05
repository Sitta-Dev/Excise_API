package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductGroupgetByConReq extends MasterRequest {

    private String TEXT_SEARCH;
    private String PRODUCT_GROUP_ID;
    private String GROUP_CODE_OLD;
}
