package com.xcs.phase2.dao.master;

import com.xcs.phase2.model.master.MasLaw;
import com.xcs.phase2.model.master.MasLawGroupSection;
import com.xcs.phase2.request.master.MasLawGroupSectiongetByConAdvReq;
import com.xcs.phase2.request.master.MasLawGroupSectiongetByConReq;
import com.xcs.phase2.request.master.MasLawGroupSectiongetByKeywordReq;
import com.xcs.phase2.request.master.MasLawGroupSectionupdDeleteReq;
import com.xcs.phase2.response.master.MasLawGroupSectioninsAllResponse;

import java.util.List;

public interface MasLawGroupSectionDAO {

    public List<MasLaw> MasLawGroupSectiongetByKeyword(MasLawGroupSectiongetByKeywordReq req);
    public List<MasLaw> MasLawGroupSectiongetByConAdv(MasLawGroupSectiongetByConAdvReq req);
    public MasLawGroupSection MasLawGroupSectiongetByCon(MasLawGroupSectiongetByConReq req);
    public MasLawGroupSectioninsAllResponse MasLawGroupSectioninsAll(MasLawGroupSection req);
    public Boolean MasLawGroupSectionpupdAll(MasLawGroupSection req);
    public Boolean MasLawGroupSectionupdDelete(MasLawGroupSectionupdDeleteReq req);

}
