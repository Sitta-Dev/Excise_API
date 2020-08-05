package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductSizegetByKeywordReq extends MasterRequest {

    private String TEXT_SEARCH;
}
