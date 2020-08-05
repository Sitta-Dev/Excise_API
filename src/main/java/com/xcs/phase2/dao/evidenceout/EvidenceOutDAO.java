package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.model.evidenceout.EvidenceOut;
import com.xcs.phase2.request.evidenceout.EvidenceOutgetByConReq;
import com.xcs.phase2.request.evidenceout.EvidenceOutupdDeleteReq;
import com.xcs.phase2.response.evidenceout.EvidenceOutinsAllResponse;

public interface EvidenceOutDAO {

	public EvidenceOut EvidenceOutgetByCon(EvidenceOutgetByConReq req);
	public EvidenceOutinsAllResponse EvidenceOutinsAll(EvidenceOut req);
	public Boolean EvidenceOutupdByCon(EvidenceOut req);
	public Boolean EvidenceOutupdDelete(EvidenceOutupdDeleteReq req);
}
