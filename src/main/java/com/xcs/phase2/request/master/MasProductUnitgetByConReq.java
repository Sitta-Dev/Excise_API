package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductUnitgetByConReq extends MasterRequest {

    private int UNIT_ID;
    private String UNIT_NAME;
//    private int UNIT_NAME_TH;
//    private int UNIT_NAME_EN;
//    private int UNIT_SHORT_NAME;
    private String USED_FOR;
    private String PRODUCT_GROUP_CODE;
}
