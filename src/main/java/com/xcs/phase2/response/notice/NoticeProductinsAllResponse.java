package com.xcs.phase2.response.notice;

import lombok.Data;

@Data
public class NoticeProductinsAllResponse extends NoticeResponse {

    private String IsSuccess;
    private String Msg;
    private int PRODUCT_ID;
}
