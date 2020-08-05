package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasLawPenaltyinsAllResponse extends MasterResponse {

    private String IsSuccess;
    private String Msg;
    private int PENALTY_ID;
}
