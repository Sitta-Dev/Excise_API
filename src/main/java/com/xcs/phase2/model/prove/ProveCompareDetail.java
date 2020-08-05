package com.xcs.phase2.model.prove;

import lombok.Data;

import java.util.List;

@Data
public class ProveCompareDetail extends ProveModel {

    private int LAWSUIT_DETAIL_ID;
    private int LAWSUIT_TYPE;
    private float FINE;
    private int COMPARE_DETAIL_ID;
    private List<ProveComparePayment> ProveComparePayment;

}
