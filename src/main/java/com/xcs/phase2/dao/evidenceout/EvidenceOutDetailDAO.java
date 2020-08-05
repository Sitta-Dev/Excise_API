package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.model.evidenceout.EvidenceOutDetail;
import com.xcs.phase2.request.evidenceout.EvidenceOutDetailupdDeleteReq;
import com.xcs.phase2.response.evidenceout.EvidenceOutDetailinsAllResponse;

import java.util.List;


public interface EvidenceOutDetailDAO {

	public EvidenceOutDetailinsAllResponse EvidenceOutDetailinsAll(List<EvidenceOutDetail> req);
	public Boolean EvidenceOutDetailupdByCon(List<EvidenceOutDetail> req);
	public Boolean EvidenceOutDetailupdDelete(EvidenceOutDetailupdDeleteReq req);
}
