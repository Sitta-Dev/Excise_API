package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasProductSizeinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int SIZE_ID;
}
