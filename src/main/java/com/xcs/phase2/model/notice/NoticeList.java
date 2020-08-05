package com.xcs.phase2.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class NoticeList extends NoticeModel {

    private int NOTICE_ID;
    private String NOTICE_CODE;
    private String NOTICE_DATE;
    private String OFFICE_NAME;
    private int IS_ARREST;
    private List<NoticeStaff> NoticeStaff;
    private List<NoticeSuspect> NoticeSuspect;

}
