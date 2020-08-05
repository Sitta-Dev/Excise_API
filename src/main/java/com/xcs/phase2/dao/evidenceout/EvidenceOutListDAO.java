package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.model.evidenceout.EvidenceOutList;
import com.xcs.phase2.request.evidenceout.EvidenceOutListgetByConAdvReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutListgetByKeywordReq;

import java.util.List;

public interface EvidenceOutListDAO {

    public List<EvidenceOutList> EvidenceOutListgetByKeyword(EvidenceOutListgetByKeywordReq req);
    public List<EvidenceOutList> EvidenceOutListgetByConAdv(EvidenceOutListgetByConAdvReq req);
}
