package com.xcs.phase2.dao.evidencein;

import com.xcs.phase2.model.evidencein.EvidenceInBook;
import com.xcs.phase2.request.evidencein.EvidenceInBookListgetByConAdvReq;
import com.xcs.phase2.request.evidencein.EvidenceInBookListgetByKeywordReq;

import java.util.List;

public interface EvidenceInBookDAO {

    public List<EvidenceInBook> EvidenceInBookListgetByKeyword(EvidenceInBookListgetByKeywordReq req);
    public List<EvidenceInBook> EvidenceInBookListgetByConAdv(EvidenceInBookListgetByConAdvReq req);
}
