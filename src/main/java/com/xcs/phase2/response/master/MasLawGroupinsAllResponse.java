package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasLawGroupinsAllResponse extends MasterResponse{

    private String IsSuccess;
    private String Msg;
    private int LAW_GROUP_ID;
}
