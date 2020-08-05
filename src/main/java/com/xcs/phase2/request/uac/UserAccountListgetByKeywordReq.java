package com.xcs.phase2.request.uac;

import lombok.Data;

@Data
public class UserAccountListgetByKeywordReq extends UacRequest {

    private String TEXT_SEARCH;
}
