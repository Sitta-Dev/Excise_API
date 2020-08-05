package com.xcs.phase2.response.prove;

import lombok.Data;

@Data
public class ProveScienceinsAllResponse extends ProveResponse {

    private String IsSuccess;
    private String Msg;
    private int SCIENCE_ID;
}
