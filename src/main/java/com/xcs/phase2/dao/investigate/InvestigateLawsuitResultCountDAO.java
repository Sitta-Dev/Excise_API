package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.model.investigate.InvestigateLawsuitResultCount;
import com.xcs.phase2.request.investigate.InvestigateLawsuitResultCountgetByLawbreakerIDReq;

public interface InvestigateLawsuitResultCountDAO {

    public InvestigateLawsuitResultCount InvestigateLawsuitResultCountgetByLawbreakerID(InvestigateLawsuitResultCountgetByLawbreakerIDReq req);
}
