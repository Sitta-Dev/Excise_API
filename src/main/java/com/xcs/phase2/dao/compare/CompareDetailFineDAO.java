package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareDetailFine;
import com.xcs.phase2.request.compare.CompareDetailFineupdDeleteReq;
import com.xcs.phase2.response.compare.CompareDetailFineinsAllResponse;

import java.util.List;

public interface CompareDetailFineDAO {

    public CompareDetailFineinsAllResponse CompareDetailFineinsAll(List<CompareDetailFine> req);
    public Boolean CompareDetailFineupdByCon(List<CompareDetailFine> req);
    public Boolean CompareDetailFineupdDelete(List<CompareDetailFineupdDeleteReq> req);
}
