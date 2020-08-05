package com.xcs.phase2.model.target;

import lombok.Data;

@Data
public class TargetItemDetail extends TargetModel {

    private int ITEM_DETAIL_ID;
    private int ITEM_ID;
    private float OLD_QTY;
    private float QTY_CASE;
    private float QTY_CASE_PERCENT;
    private float OLD_FINE;
    private float FINE;
    private float FINE_PERCENT;
    private float TREASURY_MONEY;
    private int MONTH;
    private float OLD_BRIBE;
    private float BRIBE;
    private float OLD_REWARD;
    private float REWARD;
    private float OLD_TREASURY;


}
