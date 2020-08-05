package com.xcs.phase2.dao.evidenceout;

import com.xcs.phase2.model.evidenceout.EvidenceOutStaff;
import com.xcs.phase2.request.evidenceout.EvidenceOutStaffupdDeleteReq;
import com.xcs.phase2.response.evidenceout.EvidenceOutStaffinsAllResponse;

import java.util.List;

public interface EvidenceOutStaffDAO {

    public EvidenceOutStaffinsAllResponse EvidenceOutStaffinsAll(List<EvidenceOutStaff> req);
    public boolean EvidenceOutStaffupdByCon(List<EvidenceOutStaff> req);
    public boolean EvidenceOutStaffupdDelete(EvidenceOutStaffupdDeleteReq req);
}
