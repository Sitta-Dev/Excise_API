package com.xcs.phase2.request.uac;

import lombok.Data;

@Data
public class RoleListgetByKeywordReq extends UacRequest {

    private String TEXT_SEARCH;
}
