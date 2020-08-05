package com.xcs.phase2.model.uac;

import lombok.Data;

import java.util.List;

@Data
public class UserAccount extends UacModel {

    private int USER_ACCOUNT_ID;
    private int STAFF_ID;
    private int ROLE_ID;
    private int USER_TYPE;
    private String USER_NAME;
    private String PASSWORD;
    private int IS_SIGN_ON;
    private String SIGN_ON_IP;
    private String APPROVE_CODE;
    private int IS_ACTIVE;
    private MasStaff MasStaff;
    private List<Role> Role;
    private List<UserAccountPermission> UserAccountPermission;
}
