package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.model.investigate.InvestigateDetailLocale;
import com.xcs.phase2.request.investigate.InvestigateDetailLocalupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateDetailLocalinsAllResponse;

import java.util.List;

public interface InvestigateDetailLocaleDAO {

    public InvestigateDetailLocalinsAllResponse InvestigateDetailLocalinsAll(List<InvestigateDetailLocale> req);
    public Boolean InvestigateDetailLocalupdByCon(List<InvestigateDetailLocale> req);
    public Boolean InvestigateDetailLocalupdDelete(List<InvestigateDetailLocalupdDeleteReq> req);
}
