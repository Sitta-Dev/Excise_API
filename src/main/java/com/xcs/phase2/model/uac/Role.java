package com.xcs.phase2.model.uac;

import lombok.Data;

import java.util.List;

@Data
public class Role extends UacModel {

    private int ROLE_ID;
    private String ROLE_CODE;
    private String ROLE_NAME;
    private String ROLE_DESCRIPTION;
    private int IS_ACTIVE;
    private int OPERATION_POS_ID;
    private List<RolePermission> RolePermission;
    private MasOperationPosition MasOperationPosition;

}
