package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductSize;
import com.xcs.phase2.request.master.MasProductSizegetByConAdvReq;
import com.xcs.phase2.request.master.MasProductSizegetByConReq;
import com.xcs.phase2.request.master.MasProductSizegetByKeywordReq;
import com.xcs.phase2.request.master.MasProductSizeupdDeleteReq;
import com.xcs.phase2.response.master.MasProductSizeinsAllResponse;

import java.util.List;

public interface MasProductSizeDAO {

    public List<MasProductSize> MasProductSizegetByKeyword(MasProductSizegetByKeywordReq req);
    public List<MasProductSize> MasProductSizegetByConAdv(MasProductSizegetByConAdvReq req);
    public MasProductSize MasProductSizegetByCon(MasProductSizegetByConReq req);
    public MasProductSizeinsAllResponse MasProductSizeinsAll(MasProductSize req);
    public Boolean MasProductSizeupdAll(MasProductSize req);
    public Boolean MasProductSizeupdDelete(MasProductSizeupdDeleteReq req);
}
