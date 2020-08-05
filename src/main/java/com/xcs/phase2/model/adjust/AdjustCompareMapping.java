package com.xcs.phase2.model.adjust;

import lombok.Data;

import java.util.List;

@Data
public class AdjustCompareMapping extends AdjustModel {

    private int COMPARE_MAPPING_ID;
    private int COMPARE_ID;
    private int INDICTMENT_DETAIL_ID;
    private int PAST_LAWSUIT_ID;
    private int IS_EVER_WRONG;
    private int IS_ACTIVE;
    private List<AdjustCompareDetail> AdjustCompareDetail;
    private List<AdjustCompareArrestIndictmentDetail> AdjustCompareArrestIndictmentDetail;

}
