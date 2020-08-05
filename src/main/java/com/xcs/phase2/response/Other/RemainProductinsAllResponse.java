package com.xcs.phase2.response.Other;

import com.xcs.phase2.response.Response;
import lombok.Data;

@Data
public class RemainProductinsAllResponse extends Response {

    private String IsSuccess;
    private String Msg;
    private int REMAIN_PRODUCT_ID;
}
