package com.xcs.phase2.model.uac;

import lombok.Data;

@Data
public class UserAccountPermission extends UacModel {

    private int USER_PERMISSION_ID;
    private int USER_ACCOUNT_ID;
    private String PROGRAM_CODE;
    private int IS_CREATE;
    private int IS_READ;
    private int IS_UPDATE;
    private int IS_DELETE;

}
