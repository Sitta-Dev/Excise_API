package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.model.investigate.InvestigateDetailSuspect;
import com.xcs.phase2.request.investigate.InvestigateDetailSuspectupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateDetailSuspectinsAllResponse;

import java.util.List;

public interface InvestigateDetailSuspectDAO {

    public InvestigateDetailSuspectinsAllResponse InvestigateDetailSuspectinsAll(List<InvestigateDetailSuspect> req);
    public Boolean InvestigateDetailSuspectupdDelete(List<InvestigateDetailSuspectupdDeleteReq> req);
}
