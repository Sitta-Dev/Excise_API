package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.model.adjust.AdjustCompareDetailFine;
import com.xcs.phase2.request.adjust.AdjustCompareDetailFineupdDeleteReq;
import com.xcs.phase2.response.adjust.AdjustCompareDetailFineinsAllResponse;

import java.util.List;

public interface AdjustCompareDetailFineDAO {

    public AdjustCompareDetailFineinsAllResponse AdjustCompareDetailFineinsAll(List<AdjustCompareDetailFine> req);
    public Boolean AdjustCompareDetailFineupdByCon(List<AdjustCompareDetailFine> req);
    public Boolean AdjustCompareDetailFineupdDelete(List<AdjustCompareDetailFineupdDeleteReq> req);
}
