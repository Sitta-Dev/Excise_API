package com.xcs.phase2.request.master;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class MasLawGroupgetByKeywordReq extends Request {

    private String TEXT_SEARCH;
}
