package com.xcs.phase2.dao.evidenceout;


import com.xcs.phase2.model.evidenceout.EvidenceOutInType;
import com.xcs.phase2.request.evidenceout.EvidenceOutInTypegetByConReq;

public interface EvidenceOutInTypeDAO {

	public EvidenceOutInType EvidenceOutInTypegetByCon(EvidenceOutInTypegetByConReq req);
}
