package com.xcs.phase2.dao.evidence;

import com.xcs.phase2.model.evidence.*;
import com.xcs.phase2.request.evidence.*;

import java.util.List;

public interface EvidenceDAO {

    public EvidenceAccount EvidenceAccountgetByCon(EvidenceAccountgetByConReq req);
    public EvidenceAccountWarehouse EvidenceAccountWarehoustgetByCon(EvidenceAccountWarehoustgetByConReq req);
    public List<EvidenceAccountProduct> EvidenceAccountProductgetByCon(EvidenceAccountProductgetByConReq req);
    public List<EvidenceAccountProductDetail> EvidenceAccountProductDetailgetByCon(EvidenceAccountProductDetailgetByConReq req);
    public List<EvidenceAccountHistory> EvidenceAccountHistory(EvidenceAccountHistoryReq req);
}
