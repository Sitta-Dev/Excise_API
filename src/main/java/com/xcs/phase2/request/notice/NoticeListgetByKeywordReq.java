package com.xcs.phase2.request.notice;

import lombok.Data;

@Data
public class NoticeListgetByKeywordReq extends NoticeRequest {

    private String TEXT_SEARCH;
    private String ACCOUNT_OFFICE_CODE;
}
