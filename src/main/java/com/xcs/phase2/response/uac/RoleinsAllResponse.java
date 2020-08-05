package com.xcs.phase2.response.uac;

import lombok.Data;

import java.util.List;

@Data
public class RoleinsAllResponse extends UacResponse {

    private String IsSuccess;
    private String Msg;
    private int ROLE_ID;
    private List<RolePermissionResponse> RolePermission;
}
