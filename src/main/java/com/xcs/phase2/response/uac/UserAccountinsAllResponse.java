package com.xcs.phase2.response.uac;

import lombok.Data;

import java.util.List;

@Data
public class UserAccountinsAllResponse extends UacResponse {

    private String IsSuccess;
    private String Msg;
    private int USER_ACCOUNT_ID;
    private List<UserAccountPermissionResponse> UserAccountPermission;
}
