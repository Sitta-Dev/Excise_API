package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.UserAccountList;
import com.xcs.phase2.request.uac.UserAccountListgetByConAdvReq;
import com.xcs.phase2.request.uac.UserAccountListgetByKeywordReq;

import java.util.List;

public interface UserAccountListDAO {

    public List<UserAccountList> UserAccountListgetByKeyword(UserAccountListgetByKeywordReq req);
    public List<UserAccountList> UserAccountListgetByConAdv(UserAccountListgetByConAdvReq req);
}
