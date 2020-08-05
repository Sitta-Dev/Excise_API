package com.xcs.phase2.dao.uac;


import com.xcs.phase2.model.uac.UserAccount;
import com.xcs.phase2.request.uac.UserAccountgetByConReq;
import com.xcs.phase2.request.uac.UserAccountupdDeleteReq;
import com.xcs.phase2.response.uac.UserAccountinsAllResponse;

public interface UserAccountDAO {

    public UserAccount UserAccountgetByCon(UserAccountgetByConReq req);
    public UserAccountinsAllResponse UserAccountinsAll(UserAccount req);
    public Boolean UserAccountupdByCon(UserAccount req);
    public Boolean UserAccountupdDelete(UserAccountupdDeleteReq req);
}
