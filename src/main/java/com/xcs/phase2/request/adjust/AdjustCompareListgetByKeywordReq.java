package com.xcs.phase2.request.adjust;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class AdjustCompareListgetByKeywordReq extends Request {

    private String TEXT_SEARCH;
    private String ACCOUNT_OFFICE_CODE;

}
