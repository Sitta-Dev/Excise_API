package com.xcs.phase2.request.compare;

import lombok.Data;

@Data
public class CompareVerifyCompareNoReq extends CompareRequest {

    private Integer COMPARE_NO;
    private String COMPARE_NO_YEAR;
    private String OFFICE_CODE;
    private Integer IS_OUTSIDE;

}
