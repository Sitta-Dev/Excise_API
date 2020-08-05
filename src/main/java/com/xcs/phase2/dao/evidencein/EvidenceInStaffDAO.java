package com.xcs.phase2.dao.evidencein;

import com.xcs.phase2.model.evidencein.EvidenceInStaff;
import com.xcs.phase2.request.evidencein.EvidenceInStaffgetByConReq;
import com.xcs.phase2.response.evidencein.EvidenceInStaffinsAllResponse;

import java.util.List;

public interface EvidenceInStaffDAO {

    public EvidenceInStaffinsAllResponse EvidenceInStaffinsAll(List<EvidenceInStaff> req);
    public boolean EvidenceInStaffupdByCon(List<EvidenceInStaff> req);
    public List<EvidenceInStaff> EvidenceInStaffgetByCon(EvidenceInStaffgetByConReq req);
}
