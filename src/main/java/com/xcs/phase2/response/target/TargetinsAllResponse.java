package com.xcs.phase2.response.target;

import lombok.Data;

import java.util.List;

@Data
public class TargetinsAllResponse extends TargetResponse {

    private String IsSuccess;
    private String Msg;
    private int TARGET_ID;
    private List<TargetItemResponse> TargetItem;
}
