package com.xcs.phase2.request.master;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class MasLawGroupSubSectionRulegetByKeywordReq extends Request {

    private String TEXT_SEARCH;
}
