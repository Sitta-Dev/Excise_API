package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasProductGroupinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int PRODUCT_GROUP_ID;
}
