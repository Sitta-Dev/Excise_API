package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductUnitgetByConAdvReq extends MasterRequest {

    private String TEXT_SEARCH;
    private String PRODUCT_GROUP_CODE;

}
