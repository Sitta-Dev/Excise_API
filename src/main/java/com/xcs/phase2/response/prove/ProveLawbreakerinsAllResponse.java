package com.xcs.phase2.response.prove;

import lombok.Data;

@Data
public class ProveLawbreakerinsAllResponse extends ProveResponse {

    private String IsSuccess;
    private String Msg;
    private int LAWBREAKER_ID;
}
