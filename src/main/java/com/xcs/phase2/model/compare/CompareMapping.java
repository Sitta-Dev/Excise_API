package com.xcs.phase2.model.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareMapping extends CompareModel {

    private int COMPARE_MAPPING_ID;
    private int COMPARE_ID;
    private int INDICTMENT_DETAIL_ID;
    private int PAST_LAWSUIT_ID;
    private int IS_EVER_WRONG;
    private int IS_ACTIVE;

    private List<CompareDetail> CompareDetail;
    private List<CompareArrestIndictmentDetail> CompareArrestIndictmentDetail;

}
