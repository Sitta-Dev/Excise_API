package com.xcs.phase2.model.uac;

import lombok.Data;

@Data
public class RolePermission extends UacModel {

    private int ROLE_PERMISSION_ID;
    private int ROLE_ID;
    private int MODULE_DETAIL_ID;
    private int MODULE_ID;
    private int IS_CREATE;
    private int IS_READ;
    private int IS_UPDATE;
    private int IS_DELETE;


}
