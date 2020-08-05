package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.UserAccountPermission;
import com.xcs.phase2.request.uac.UserAccountPermissionCheckPermissionReq;

import java.util.List;

public interface UserAccountPermissionDAO {

    public List<UserAccountPermission> UserAccountPermissionCheckPermission(UserAccountPermissionCheckPermissionReq req);
}
