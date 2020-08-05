package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductSubSetType;
import com.xcs.phase2.request.master.MasProductSubSetTypegetByConReq;
import com.xcs.phase2.request.master.MasProductSubSetTypeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductSubSetTypeinsAllResponse;

import java.util.List;

public interface MasProductSubSetTypeDAO {

    public List<MasProductSubSetType> MasProductSubSetTypegetByCon(MasProductSubSetTypegetByConReq req);
    public MasProductSubSetTypeinsAllResponse MasProductSubSetTypeinsAll(MasProductSubSetType req);
    public Boolean MasProductSubSetTypeupdAll(MasProductSubSetType req);
    public Boolean MasProductSubSetTypeupdDelete(MasProductSubSetTypeupdDeleteReq req);
}
