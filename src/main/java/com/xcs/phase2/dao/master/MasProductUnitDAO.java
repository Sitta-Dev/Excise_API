package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductUnit;
import com.xcs.phase2.request.master.MasProductUnitgetByConAdvReq;
import com.xcs.phase2.request.master.MasProductUnitgetByConReq;
import com.xcs.phase2.request.master.MasProductUnitgetByKeywordReq;
import com.xcs.phase2.request.master.MasProductUnitupdDeleteReq;
import com.xcs.phase2.response.master.MasProductUnitinsAllResponse;

import java.util.List;

public interface MasProductUnitDAO {

    public List<MasProductUnit> MasProductUnitgetByKeyword(MasProductUnitgetByKeywordReq req);
    public List<MasProductUnit> MasProductUnitgetByConAdv(MasProductUnitgetByConAdvReq req);
    public MasProductUnit MasProductUnitgetByCon(MasProductUnitgetByConReq req);
    public MasProductUnitinsAllResponse MasProductUnitinsAll(MasProductUnit req);
    public Boolean MasProductUnitupdAll(MasProductUnit req);
    public Boolean MasProductUnitupdDelete(MasProductUnitupdDeleteReq req);
}
