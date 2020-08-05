package com.xcs.phase2.dao.master;

import com.xcs.phase2.model.master.MasLawPenalty;
import com.xcs.phase2.request.master.MasLawGroupSectionPenaltygetByConReq;
import com.xcs.phase2.request.master.MasLawPenaltygetByConReq;
import com.xcs.phase2.response.master.MasLawPenaltyinsAllResponse;

public interface MasLawPenaltyDAO {

    public MasLawPenalty MasLawPenaltygetByCon(MasLawPenaltygetByConReq req);
    public MasLawPenalty MasLawGroupSectionPenaltygetByCon(MasLawGroupSectionPenaltygetByConReq req);
    public MasLawPenaltyinsAllResponse MasLawPenaltyinsAll(MasLawPenalty req);
    public Boolean MasLawPenaltyupdAll(MasLawPenalty req);

}
