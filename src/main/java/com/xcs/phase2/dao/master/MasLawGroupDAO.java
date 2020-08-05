package com.xcs.phase2.dao.master;

import com.xcs.phase2.model.master.MasLawGroup;
import com.xcs.phase2.request.master.MasLawGroupgetByConAdvReq;
import com.xcs.phase2.request.master.MasLawGroupgetByConReq;
import com.xcs.phase2.request.master.MasLawGroupgetByKeywordReq;
import com.xcs.phase2.request.master.MasLawGroupupdDeleteReq;
import com.xcs.phase2.response.master.MasLawGroupinsAllResponse;

import java.util.List;

public interface MasLawGroupDAO {

    public List<MasLawGroup> MasLawGroupgetByKeyword(MasLawGroupgetByKeywordReq req);
    public List<MasLawGroup> MasLawGroupgetByConAdv(MasLawGroupgetByConAdvReq req);
    public MasLawGroup MasLawGroupgetByCon(MasLawGroupgetByConReq req);
    public MasLawGroupinsAllResponse MasLawGroupinsAll(MasLawGroup req);
    public Boolean MasMasLawGroupupdAll(MasLawGroup req);
    public Boolean MasLawGroupupdDelete(MasLawGroupupdDeleteReq req);

}
