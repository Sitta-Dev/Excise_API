package com.xcs.phase2.model.target;

import lombok.Data;

import java.util.List;

@Data
public class TargetItem extends TargetModel {

    private int ITEM_ID;
    private int TARGET_ID;
    private int PRODUCT_GROUP_ID;
    private String TARGET_ITEM_DATE;
    private Float OLD_QTY;
    private Float OLD_FINE;
    private int SEQUENCE;
    private int IS_SEND;
    private int IS_ACTIVE;
    private int LAWSUIT_TYPE_TARGET;
    private Float OLD_BRIBE;
    private Float OLD_REWARD;
    private Float OLD_TREASURY;
    private List<TargetItemDetail> TargetItemDetail;

}
