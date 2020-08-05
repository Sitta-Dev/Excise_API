package com.xcs.phase2.response.prove;

import lombok.Data;

@Data
public class ProveStaffinsAllResponse extends ProveResponse {

    private String IsSuccess;
    private String Msg;
    private int STAFF_ID;
}
