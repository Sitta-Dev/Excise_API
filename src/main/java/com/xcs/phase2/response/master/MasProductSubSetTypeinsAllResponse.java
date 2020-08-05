package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasProductSubSetTypeinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int PRODUCT_SUBSETTYPE_ID;
}
