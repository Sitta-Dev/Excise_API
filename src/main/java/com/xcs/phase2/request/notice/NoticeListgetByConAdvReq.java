package com.xcs.phase2.request.notice;

import lombok.Data;

@Data
public class NoticeListgetByConAdvReq extends NoticeRequest {

    private String NOTICE_CODE;
    private String DATE_START_FROM;
    private String DATE_START_TO;
    //private String SUSPECT_NAME;
    private String STAFF_NAME;
    private String OFFICE_NAME;
    private String IS_ARREST;
    private String ACCOUNT_OFFICE_CODE;

}
