package com.xcs.phase2.response.investigate;

import lombok.Data;

@Data
public class InvestigateinsAllResponse extends InvestigateResponse {

    private String IsSuccess;
    private String Msg;
    private int INVESTIGATE_ID;
}
