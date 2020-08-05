package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.model.evidenceout.EvidenceInventory;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByConDetailReq;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByConReq;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByEvidenceInItemCodeReq;
import com.xcs.phase2.request.evidenceout.EvidenceInventoryListgetByOfficeNameReq;

import java.util.List;

public interface EvidenceInventoryDAO {

    public List<EvidenceInventory> EvidenceInventoryListgetByOfficeName(EvidenceInventoryListgetByOfficeNameReq req);
    public List<EvidenceInventory> EvidenceInventoryListgetByEvidenceInItemCode(EvidenceInventoryListgetByEvidenceInItemCodeReq req);
    public List<EvidenceInventory> EvidenceInventoryListgetByCon(EvidenceInventoryListgetByConReq req);
    public List<EvidenceInventory> EvidenceInventoryListgetByConDetail(EvidenceInventoryListgetByConDetailReq req);
}
