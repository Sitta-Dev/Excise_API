package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.model.investigate.InvestigateDetail;
import com.xcs.phase2.request.investigate.InvestigateDetailgetByConReq;
import com.xcs.phase2.request.investigate.InvestigateDetailupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateDetailinsAllResponse;

public interface InvestigateDetailDAO {

    public InvestigateDetail InvestigateDetailgetByCon(InvestigateDetailgetByConReq req);
    public InvestigateDetailinsAllResponse InvestigateDetailinsAll(InvestigateDetail req);
    public Boolean InvestigateDetailupdByCon(InvestigateDetail req);
    public Boolean InvestigateDetailupdDelete(InvestigateDetailupdDeleteReq req);
}
