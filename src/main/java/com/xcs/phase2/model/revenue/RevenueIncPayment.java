package com.xcs.phase2.model.revenue;

import lombok.Data;

import java.util.List;

@Data
public class RevenueIncPayment extends RevenueModel {

    private List<IncPayment> IncPayment;
    private List<RevenueIncPaymentType> RevenueIncPaymentType;
    private List<RevenueIncCompareDetail> RevenueIncCompareDetail;
}
