package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasProductMappinggetByKeywordReq extends MasterRequest {

    private String TEXT_SEARCH;
}
