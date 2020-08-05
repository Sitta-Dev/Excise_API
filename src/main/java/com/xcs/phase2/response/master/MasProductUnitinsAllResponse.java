package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasProductUnitinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int UNIT_ID;
}
