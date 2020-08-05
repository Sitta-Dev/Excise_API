package com.xcs.phase2.response.notice;

import lombok.Data;

@Data
public class NoticeSupectinsAllResponse extends NoticeResponse{

    private String IsSuccess;
    private String Msg;
    private int SUSPECT_ID;
}
