package com.xcs.phase2.response.Other;

import com.xcs.phase2.response.Response;
import lombok.Data;

@Data
public class InsertNewVersionResponse extends Response {

    private String IsSuccess;
    private String Msg;
    private int VERSION_ID;
}
