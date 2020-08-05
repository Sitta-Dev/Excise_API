package com.xcs.phase2.dao.master;

import com.xcs.phase2.model.master.MasLawGroupSubSection;
import com.xcs.phase2.request.master.MasLawGroupSubSectiongetByConReq;
import com.xcs.phase2.response.master.MasLawGroupSubSectioninsAllResponse;

public interface MasLawGroupSubSectionDAO {

    public MasLawGroupSubSection MasLawGroupSubSectiongetByCon(MasLawGroupSubSectiongetByConReq req);
    public MasLawGroupSubSectioninsAllResponse MasLawGroupSubSectioninsAll(MasLawGroupSubSection req);
    public Boolean MasLawGroupSubSectionupdAll(MasLawGroupSubSection req);
}
