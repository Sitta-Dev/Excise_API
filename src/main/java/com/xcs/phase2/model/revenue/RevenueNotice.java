package com.xcs.phase2.model.revenue;

import lombok.Data;

@Data
public class RevenueNotice extends RevenueModel {

    private int NOTICE_ID;
    private int ARREST_ID;
    private String NOTICE_CODE;
    private int IS_ARREST;
    private int IS_ACTIVE;
}
