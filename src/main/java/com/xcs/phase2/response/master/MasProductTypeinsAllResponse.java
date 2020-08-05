package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasProductTypeinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int PRODUCT_TYPE_ID;
}
