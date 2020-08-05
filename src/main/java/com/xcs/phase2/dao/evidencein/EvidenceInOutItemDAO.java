package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.request.evidencein.EvidenceInOutItemupdDeleteIsReturnReq;
import com.xcs.phase2.request.evidencein.EvidenceInOutItemupdIsReturnReq;

import java.util.List;

public interface EvidenceInOutItemDAO {

    public Boolean EvidenceInOutItemupdIsReturn(List<EvidenceInOutItemupdIsReturnReq> req);
    public Boolean EvidenceInOutItemupdDeleteIsReturn(List<EvidenceInOutItemupdDeleteIsReturnReq> req);
}
