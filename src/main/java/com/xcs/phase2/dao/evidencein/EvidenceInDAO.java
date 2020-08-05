package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.model.evidencein.EvidenceIn;
import com.xcs.phase2.request.evidencein.EvidenceIngetByConReq;
import com.xcs.phase2.request.evidencein.EvidenceInupdDeleteReq;
import com.xcs.phase2.response.evidencein.EvidenceIninsAllResponse;

public interface EvidenceInDAO {

    public EvidenceIn EvidenceIngetByCon(EvidenceIngetByConReq req);
    public EvidenceIninsAllResponse EvidenceIninsAll(EvidenceIn req);
    public Boolean EvidenceInupdByCon(EvidenceIn req);
    public Boolean EvidenceInupdDelete(EvidenceInupdDeleteReq req);
}
