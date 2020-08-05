package com.xcs.phase2.response.notice;

import lombok.Data;

@Data
public class NoticeStaffinsAllResponse extends NoticeResponse {

    private String IsSuccess;
    private String Msg;
    private int STAFF_ID;
}
