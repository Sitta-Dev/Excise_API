package com.xcs.phase2.model.prove;

import lombok.Data;

import java.util.List;

@Data
public class ProveCompare extends ProveModel {

    private int PROVE_ID;
    private int INDICTMENT_ID;
    private int LAWSUIT_ID;
    private int COMPARE_ID;
    private List<ProveCompareDetail> ProveCompareDetail;


}
