package com.xcs.phase2.model.notice;

import lombok.Data;

@Data
public class NoticeInformer extends NoticeModel {

    private int INFORMER_ID;
    private int NOTICE_ID;
    private int TITLE_ID;
    private int SUB_DISTRICT_ID;
    private int INFORMER_STATUS;
    private String TITLE_NAME_TH;
    private String TITLE_NAME_EN;
    private String TITLE_SHORT_NAME_TH;
    private String TITLE_SHORT_NAME_EN;
    private String FIRST_NAME;
    private String MIDDLE_NAME;
    private String LAST_NAME;
    private String OTHER_NAME;
    private String ID_CARD;
    private int AGE;
    private String CAREER;
    private String POSITION;
    private String PERSON_DESC;
    private String EMAIL;
    private String TEL_NO;
    private String INFORMER_INFO;
    private String GPS;
    private String ADDRESS_NO;
    private String VILLAGE_NO;
    private String BUILDING_NAME;
    private String ROOM_NO;
    private String FLOOR;
    private String VILLAGE_NAME;
    private String ALLEY;
    private String LANE;
    private String ROAD;
    private String INFORMER_PHOTO;
    private String INFORMER_FINGER_PRINT;
    private int IS_ACTIVE;

}
