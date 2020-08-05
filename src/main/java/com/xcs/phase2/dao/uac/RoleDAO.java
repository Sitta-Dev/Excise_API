package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.Role;
import com.xcs.phase2.request.uac.RolegetByConReq;
import com.xcs.phase2.request.uac.RoleupdDeleteReq;
import com.xcs.phase2.response.uac.RoleinsAllResponse;

public interface RoleDAO {

    public Role RolegetByCon(RolegetByConReq req);
    public RoleinsAllResponse RoleinsAll(Role req);
    public Boolean RoleupdByCon(Role req);
    public Boolean RoleupdDelete(RoleupdDeleteReq req);
}
