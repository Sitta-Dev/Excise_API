package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductType;
import com.xcs.phase2.request.master.MasProductTypegetByConReq;
import com.xcs.phase2.request.master.MasProductTypeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductTypeinsAllResponse;

import java.util.List;

public interface MasProductTypeDAO {

    public List<MasProductType> MasProductTypegetByCon(MasProductTypegetByConReq req);
    public MasProductTypeinsAllResponse MasProductTypeinsAll(MasProductType req);
    public Boolean MasProductTypeupdAll(MasProductType req);
    public Boolean MasProductTypeupdDelete(MasProductTypeupdDeleteReq req);
}
