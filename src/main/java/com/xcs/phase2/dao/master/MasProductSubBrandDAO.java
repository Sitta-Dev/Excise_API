package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductSubBrand;
import com.xcs.phase2.request.master.MasProductSubBrandgetByConReq;
import com.xcs.phase2.request.master.MasProductSubBrandupdDeleteReq;
import com.xcs.phase2.response.master.MasProductSubBrandinsAllResponse;

import java.util.List;

public interface MasProductSubBrandDAO {

    public List<MasProductSubBrand> MasProductSubBrandgetByCon(MasProductSubBrandgetByConReq req);
    public MasProductSubBrandinsAllResponse MasProductSubBrandinsAll(MasProductSubBrand req);
    public Boolean MasProductSubBrandupdAll(MasProductSubBrand req);
    public Boolean MasProductSubBrandupdDelete(MasProductSubBrandupdDeleteReq req);
}
