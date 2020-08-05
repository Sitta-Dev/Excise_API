package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.model.evidencein.EvidenceInStockBalance;
import com.xcs.phase2.model.evidencein.EvidenceInventoryList;
import com.xcs.phase2.model.evidenceout.EvidenceOutStockBalanceByLawsuitNo;
import com.xcs.phase2.request.evidencein.EvidenceInventoryListgetByLawsuitNoReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutStockBalancegetByEvidenceOutIdReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutStockBalancegetByLawsuitNoReq;

import java.util.List;

public interface EvidenceInStockBalanceDAO {

    public Boolean EvidenceInStockBalanceupdByCon(List<EvidenceInStockBalance> req);
    public List<EvidenceInventoryList> EvidenceInventoryListgetByLawsuitNo(EvidenceInventoryListgetByLawsuitNoReq req);
    public List<EvidenceOutStockBalanceByLawsuitNo> EvidenceOutStockBalanceByLawsuitNo(EvidenceOutStockBalancegetByLawsuitNoReq req);
    public List<EvidenceOutStockBalanceByLawsuitNo> EvidenceOutStockBalanceByEvidenceOutId(EvidenceOutStockBalancegetByEvidenceOutIdReq req);
}
