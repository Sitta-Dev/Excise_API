package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.model.investigate.Investigate;
import com.xcs.phase2.request.investigate.InvestigategetByConReq;
import com.xcs.phase2.request.investigate.InvestigateupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateinsAllResponse;

public interface InvestigateDAO {

    public Investigate InvestigategetByCon(InvestigategetByConReq req);
    public InvestigateinsAllResponse InvestigateinsAll(Investigate req);
    public Boolean InvestigateupdByCon(Investigate req);
    public Boolean InvestigateupdDelete(InvestigateupdDeleteReq req);
}
