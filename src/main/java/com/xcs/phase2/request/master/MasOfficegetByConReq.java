package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasOfficegetByConReq extends MasterRequest {

    private String OFFICE_ID;
    private String TEXT_SEARCH;
}
