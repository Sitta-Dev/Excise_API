package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductUnitgetByConAdvReq extends MasterRequest {

    private String UNIT_NAME_TH;
    private String UNIT_NAME_EN;
    private String UNIT_SHORT_NAME;
    private int IS_ACTIVE;

}
