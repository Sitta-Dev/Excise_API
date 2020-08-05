package com.xcs.phase2.dao.investigate;


import com.xcs.phase2.model.investigate.InvestigateDetailProduct;
import com.xcs.phase2.request.investigate.InvestigateDetailProductupdDeleteReq;
import com.xcs.phase2.response.investigate.InvestigateDetailProductinsAllResponse;

import java.util.List;

public interface InvestigateDetailProductDAO {

    public InvestigateDetailProductinsAllResponse InvestigateDetailProductinsAll(List<InvestigateDetailProduct> req);
    public Boolean InvestigateDetailProductupdByCon(List<InvestigateDetailProduct> req);
    public Boolean InvestigateDetailProductupdDelete(List<InvestigateDetailProductupdDeleteReq> req);
}
