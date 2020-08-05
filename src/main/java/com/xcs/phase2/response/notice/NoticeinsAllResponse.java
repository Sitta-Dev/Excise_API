package com.xcs.phase2.response.notice;

import lombok.Data;

import java.util.List;

@Data
public class NoticeinsAllResponse extends NoticeResponse {

    private String IsSuccess;
    private String Msg;
    private int NOTICE_ID;
    private List<NoticeInformerResponse> NoticeInformer;
    private List<NoticeStaffResponse> NoticeStaff;
    private List<NoticeLocaleResponse> NoticeLocale;
    private List<NoticeProductResponse> NoticeProduct;
    private List<NoticeSuspectResponse> NoticeSuspect;
}
