package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductBrand;
import com.xcs.phase2.request.master.MasProductBrandgetByConReq;
import com.xcs.phase2.request.master.MasProductBrandupdDeleteReq;
import com.xcs.phase2.response.master.MasProductBrandinsAllResponse;

import java.util.List;

public interface MasProductBrandDAO {

    public List<MasProductBrand> MasProductBrandgetByCon(MasProductBrandgetByConReq req);
    public MasProductBrandinsAllResponse MasProductBrandinsAll(MasProductBrand req);
    public Boolean MasProductBrandupdAll(MasProductBrand req);
    public Boolean MasProductBrandupdDelete(MasProductBrandupdDeleteReq req);
}
