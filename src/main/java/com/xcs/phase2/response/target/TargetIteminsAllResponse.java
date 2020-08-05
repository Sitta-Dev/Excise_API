package com.xcs.phase2.response.target;

import lombok.Data;

import java.util.List;

@Data
public class TargetIteminsAllResponse extends TargetResponse {

    private String IsSuccess;
    private String Msg;
    private List<TargetItemResponse> TargetItem;
}
