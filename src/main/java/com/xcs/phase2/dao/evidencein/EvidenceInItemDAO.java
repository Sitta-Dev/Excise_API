package com.xcs.phase2.dao.evidencein;

import com.xcs.phase2.model.evidencein.EvidenceInItem;
import com.xcs.phase2.request.evidencein.EvidenceInItemupdDeleteReq;
import com.xcs.phase2.response.evidencein.EvidenceInIteminsAllResponse;

import java.util.List;


public interface EvidenceInItemDAO {

	public EvidenceInIteminsAllResponse EvidenceInIteminsAll(List<EvidenceInItem> req);
	public Boolean EvidenceInItemupdByCon(List<EvidenceInItem> req);
	public Boolean EvidenceInItemupdDelete(List<EvidenceInItemupdDeleteReq> req);
}
