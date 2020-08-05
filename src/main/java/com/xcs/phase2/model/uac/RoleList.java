package com.xcs.phase2.model.uac;

import lombok.Data;

@Data
public class RoleList extends UacModel {

    private int ROLE_ID;
    private String ROLE_CODE;
    private String ROLE_NAME;
    private String ROLE_DESCRIPTION;
    private int IS_ACTIVE;

    // new field
    private MasOperationPosition masOperationPosition;

}
