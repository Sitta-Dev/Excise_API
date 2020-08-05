package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.model.investigate.InvestigateDetailStaff;
import com.xcs.phase2.request.investigate.InvestigateDetailStaffupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateDetailStaffinsAllResponse;

import java.util.List;

public interface InvestigateDetailStaffDAO {

    public InvestigateDetailStaffinsAllResponse InvestigateDetailStaffinsAll(List<InvestigateDetailStaff> req);
    public Boolean InvestigateDetailStaffupdByCon(List<InvestigateDetailStaff> req);
    public Boolean InvestigateDetailStaffupdDelete(List<InvestigateDetailStaffupdDeleteReq> req);
}
