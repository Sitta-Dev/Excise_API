package com.xcs.phase2.response.target;

import lombok.Data;

import java.util.List;

@Data
public class TargetItemResponse extends TargetResponse {

    private int ITEM_ID;
    private List<TargetItemDetailResponse> TargetItemDetail;
}
