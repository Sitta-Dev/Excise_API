package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductSizegetByConAdvReq extends MasterRequest {

    private int SIZE_ID;
    private String SIZE_NAME_TH;
    private String SIZE_NAME_EN;
    private String SIZE_SHORT_NAME;

}
