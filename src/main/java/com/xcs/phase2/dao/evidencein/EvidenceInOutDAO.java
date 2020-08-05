package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.model.evidencein.EvidenceInOut;
import com.xcs.phase2.request.evidencein.EvidenceInOutgetByWarehouseIDReq;

import java.util.List;

public interface EvidenceInOutDAO {

    public List<EvidenceInOut> EvidenceInOutgetByWarehouseID(EvidenceInOutgetByWarehouseIDReq req);
}
