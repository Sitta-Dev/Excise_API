package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductModel;
import com.xcs.phase2.request.master.MasProductModelgetByConReq;
import com.xcs.phase2.request.master.MasProductModelupdDeleteReq;
import com.xcs.phase2.response.master.MasProductModelinsAllResponse;

import java.util.List;

public interface MasProductModelDAO {

    public List<MasProductModel> MasProductModelgetByCon(MasProductModelgetByConReq req);
    public MasProductModelinsAllResponse MasProductModelinsAll(MasProductModel req);
    public Boolean MasProductModelupdAll(MasProductModel req);
    public Boolean MasProductModelupdDelete(MasProductModelupdDeleteReq req);
}
