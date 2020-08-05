package com.xcs.phase2.request.target;

import lombok.Data;

import java.util.List;

@Data
public class TargetupdByConReq extends TargetRequest {

    private int ITEM_ID;
    private int TARGET_ID;
    private int PRODUCT_GROUP_ID;
    private String TARGET_ITEM_DATE;
    private float OLD_QTY;
    private float OLD_FINE;
    private int SEQUENCE;
    private int IS_SEND;
    private int IS_ACTIVE;
    private int LAWSUIT_TYPE_TARGET;
    private float OLD_BRIBE;
    private float OLD_REWARD;
    private float OLD_TREASURY;
    private List<com.xcs.phase2.model.target.TargetItemDetail> TargetItemDetail;
}
