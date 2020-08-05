package com.xcs.phase2.response.prove;

import lombok.Data;

@Data
public class ProveProductinsAllResponse extends ProveResponse {

    private String IsSuccess;
    private String Msg;
    private int PRODUCT_ID;
}
