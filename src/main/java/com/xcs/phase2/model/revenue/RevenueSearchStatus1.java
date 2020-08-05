package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class RevenueSearchStatus1 extends RevenueModel {

    private int REVENUE_ID;
    private String REVENUE_CODE;
    private List<RevenueSearchStatus1Detail> RevenueDetail;
}
