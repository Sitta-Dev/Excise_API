package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasLawGuiltbaseFineinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int FINE_ID;
}
