package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveEvidenceJoinCase;
import com.xcs.phase2.request.prove.ProveEvidenceJoinCasegetByConReq;

import java.util.List;

public interface ProveEvidenceJoinCaseDAO {

    public List<ProveEvidenceJoinCase> ProveEvidenceJoinCasegetByCon(ProveEvidenceJoinCasegetByConReq req);
}
