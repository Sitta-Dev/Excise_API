package com.xcs.phase2.model.target;

import lombok.Data;

@Data
public class TargetList extends TargetModel {

    private int TARGET_ID;
    private String TARGET_CODE;
    private int IS_SEND;
    private String BUDGET_YEAR;
    private int SEQUENCE;
    private String TARGET_ITEM_DATE;
    private String OFFICE_NAME;
    private String PRODUCT_GROUP_NAME;
    private int LAWSUIT_TYPE_TARGET;
    private int ITEM_ID;
    private int IS_ACTIVE;

}
