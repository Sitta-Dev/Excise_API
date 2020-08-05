package com.xcs.phase2.model.target;

import lombok.Data;

@Data
public class TargetgetByCon extends TargetModel {

    private String TARGET_CODE;
    private int SEQUENCE;
    private String BUDGET_YEAR;
    private String PRODUCT_GROUP_NAME;
    private int LAWSUIT_TYPE_TARGET;
    private String OFFICE_NAME;
    private int OLD_QTY;
    private float SUM_FINE;
    private int MONTH;
    private int OLD_QTY_MONTH;
    private float QTY_CASE;
    private float FINE;
    private float BRIBE;
    private float REWARD;
    private float TREASURY_MONEY;
    private int ITEM_DETAIL_ID;
    private float FINE_PERCENT;
    private float QTY_CASE_PERCENT;

}
