package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductSubType;
import com.xcs.phase2.request.master.MasProductSubTypegetByConReq;
import com.xcs.phase2.request.master.MasProductSubTypeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductSubTypeinsAllResponse;

import java.util.List;

public interface MasProductSubTypeDAO {

    public List<MasProductSubType> MasProductSubTypegetByCon(MasProductSubTypegetByConReq req);
    public MasProductSubTypeinsAllResponse MasProductSubTypeinsAll(MasProductSubType req);
    public Boolean MasProductSubTypeupdAll(MasProductSubType req);
    public Boolean MasProductSubTypeupdDelete(MasProductSubTypeupdDeleteReq req);
}
