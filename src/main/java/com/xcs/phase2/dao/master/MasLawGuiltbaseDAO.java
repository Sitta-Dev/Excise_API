package com.xcs.phase2.dao.master;

import com.xcs.phase2.model.master.MasLawGuiltbase;
import com.xcs.phase2.response.master.MasLawGuiltbaseinsAllResponse;

public interface MasLawGuiltbaseDAO {

    public MasLawGuiltbaseinsAllResponse MasLawGuiltbaseinsAll(MasLawGuiltbase req);
    public Boolean MasLawGuiltbaseupdAll(MasLawGuiltbase req);

}
