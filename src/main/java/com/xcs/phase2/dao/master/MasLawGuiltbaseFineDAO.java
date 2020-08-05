package com.xcs.phase2.dao.master;

import com.xcs.phase2.model.master.MasLawGuiltbaseFine;
import com.xcs.phase2.request.master.MasLawGuiltbaseFinegetByConReq;
import com.xcs.phase2.response.master.MasLawGuiltbaseFineinsAllResponse;

import java.util.List;

public interface MasLawGuiltbaseFineDAO {

    public List<MasLawGuiltbaseFine> MasLawGuiltbaseFinegetByCon(MasLawGuiltbaseFinegetByConReq req);
    public MasLawGuiltbaseFineinsAllResponse MasLawGuiltbaseFineinsAll(MasLawGuiltbaseFine req);
    public Boolean MasLawGuiltbaseFineupdAll(MasLawGuiltbaseFine req);

}
