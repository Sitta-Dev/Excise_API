package com.xcs.phase2.request.uac;

import lombok.Data;

@Data
public class UserAccountPermissionCheckPermissionReq extends UacRequest {
    private int USER_ACCOUNT_ID;
    private String PROGRAM_CODE;
}
