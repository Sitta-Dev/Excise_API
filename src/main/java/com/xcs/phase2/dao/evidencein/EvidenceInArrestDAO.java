package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.model.evidencein.EvidenceInArrest;
import com.xcs.phase2.request.evidencein.EvidenceInArrestgetByProveIDReq;

import java.util.List;

public interface EvidenceInArrestDAO {

    public List<EvidenceInArrest> EvidenceInArrestgetByProveID(EvidenceInArrestgetByProveIDReq req);
}
