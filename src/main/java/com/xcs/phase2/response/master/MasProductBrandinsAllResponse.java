package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasProductBrandinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int PRODUCT_BRAND_ID;
}
