package com.xcs.phase2.response.prove;

import lombok.Data;

@Data
public class ProveinsAllResponse extends ProveResponse {

    private String IsSuccess;
    private String Msg;
    private int PROVE_ID;
}
