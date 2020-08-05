package com.xcs.phase2.request.target;

import lombok.Data;

@Data
public class TargetgetByConReq extends TargetRequest {

    private int ITEM_ID;
    private String OFFICE_CODE;
}
