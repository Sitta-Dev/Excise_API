package com.xcs.phase2.request.uac;

import lombok.Data;

@Data
public class RoleListgetByConAdvReq extends UacRequest {

    private String ROLE_CODE;
    private String ROLE_NAME;
}
