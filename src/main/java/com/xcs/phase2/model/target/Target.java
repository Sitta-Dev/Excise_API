package com.xcs.phase2.model.target;

import lombok.Data;

import java.util.List;

@Data
public class Target extends TargetModel {

    private int TARGET_ID;
    private int OFFICE_ID;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private String TARGET_CODE;
    private String BUDGET_YEAR;
    private String TARGET_DATE;
    private int IS_ACTIVE;
    private List<TargetItem> TargetItem;

}
