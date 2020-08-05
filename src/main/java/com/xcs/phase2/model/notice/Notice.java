package com.xcs.phase2.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class Notice extends NoticeModel {

    private int NOTICE_ID;
    private int ARREST_ID;
    private int OFFICE_ID;
    private String NOTICE_CODE;
    private String OFFICE_CODE;
    private String OFFICE_NAME;
    private String NOTICE_DATE;
    private int NOTICE_DUE;
    private String NOTICE_DUE_DATE;
    private int COMMUNICATION_CHANNEL;
    private int IS_ARREST;
    private int IS_AUTHORITY;
    private int IS_ACTIVE;
    private int IS_MATCH;
    private String CREATE_DATE;
    private int CREATE_USER_ACCOUNT_ID;
    private String UPDATE_DATE;
    private int UPDATE_USER_ACCOUNT_ID;
    private List<NoticeInformer> NoticeInformer;
    private List<NoticeStaff> NoticeStaff;
    private List<NoticeLocale> NoticeLocale;
    private List<NoticeProduct> NoticeProduct;
    private List<NoticeSuspect> NoticeSuspect;

}
