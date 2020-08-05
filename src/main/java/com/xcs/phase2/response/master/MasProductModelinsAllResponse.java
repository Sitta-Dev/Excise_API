package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasProductModelinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int PRODUCT_MODEL_ID;
}
