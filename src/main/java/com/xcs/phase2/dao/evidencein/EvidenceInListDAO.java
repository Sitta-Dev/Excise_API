package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.model.evidencein.EvidenceInList;
import com.xcs.phase2.request.evidencein.EvidenceInListgetByConAdvReq;
import com.xcs.phase2.request.evidencein.EvidenceInListgetByKeywordReq;

import java.util.List;

public interface EvidenceInListDAO {

    public List<EvidenceInList> EvidenceInListgetByKeyword(EvidenceInListgetByKeywordReq req);
    public List<EvidenceInList> EvidenceInListgetByConAdv(EvidenceInListgetByConAdvReq req);
}
