package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductMappinggetByConAdvReq extends MasterRequest {

    private String PRODUCT_GROUP_ID;
    private String PRODUCT_CODE;
    private String PRODUCT_NAME_DESC;

}
