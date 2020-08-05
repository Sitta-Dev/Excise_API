package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.model.evidenceout.EvidenceOutItem;
import com.xcs.phase2.model.evidenceout.EvidenceOutItemNew;
import com.xcs.phase2.request.evidenceout.EvidenceOutItemupdDeleteReq;
import com.xcs.phase2.response.evidenceout.EvidenceOutIteminsAllResponse;

import java.util.List;



public interface EvidenceOutItemDAO {

	public EvidenceOutIteminsAllResponse EvidenceOutIteminsAll(List<EvidenceOutItemNew> req);
	public Boolean EvidenceOutItemupdByCon(List<EvidenceOutItem> req);
	public Boolean EvidenceOutItemupdDelete(List<EvidenceOutItemupdDeleteReq> req);
}
