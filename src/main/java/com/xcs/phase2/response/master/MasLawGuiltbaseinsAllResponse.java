package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasLawGuiltbaseinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int GUILTBASE_ID;
}
