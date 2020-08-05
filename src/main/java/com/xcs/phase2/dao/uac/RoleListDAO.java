package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.RoleList;
import com.xcs.phase2.request.uac.RoleListgetByConAdvReq;
import com.xcs.phase2.request.uac.RoleListgetByKeywordReq;

import java.util.List;

public interface RoleListDAO {

    public List<RoleList> RoleListgetByKeyword(RoleListgetByKeywordReq req);
    public List<RoleList> RoleListgetByConAdv(RoleListgetByConAdvReq req);
}
