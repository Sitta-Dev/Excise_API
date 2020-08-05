package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductPRCgetByConReq extends MasterRequest {

    private String DUTY_CODE;
    private String PRODUCT_CODE;
}
