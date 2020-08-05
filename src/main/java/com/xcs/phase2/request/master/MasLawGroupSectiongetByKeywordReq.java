package com.xcs.phase2.request.master;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class MasLawGroupSectiongetByKeywordReq extends Request {

    private String TEXT_SEARCH;
}
