package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.model.investigate.InvestigateList;
import com.xcs.phase2.request.investigate.InvestigateListgetByConAdvReq;
import com.xcs.phase2.request.investigate.InvestigateListgetByKeywordReq;

import java.util.List;

public interface InvestigateListDAO {

    public List<InvestigateList> InvestigateListgetByKeyword(InvestigateListgetByKeywordReq req);
    public List<InvestigateList> InvestigateListgetByConAdv(InvestigateListgetByConAdvReq req);
}
