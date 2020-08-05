package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.model.evidenceout.EvidenceOutIn;
import com.xcs.phase2.request.evidenceout.EvidenceOutIngetByKeywordReq;

import java.util.List;



public interface EvidenceOutInDAO {

	public List<EvidenceOutIn> EvidenceOutIngetByKeyword(EvidenceOutIngetByKeywordReq req);
}
