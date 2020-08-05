package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasStaffgetByConReq extends MasterRequest {

    private String STAFF_ID;
    private String TEXT_SEARCH;
}
