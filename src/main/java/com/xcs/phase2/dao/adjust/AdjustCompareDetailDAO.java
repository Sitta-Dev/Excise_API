package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.model.adjust.AdjustCompareDetail;
import com.xcs.phase2.request.adjust.AdjustCompareDetailupdDeleteReq;
import com.xcs.phase2.response.adjust.AdjustCompareDetailinsAllResponse;

public interface AdjustCompareDetailDAO {

    public AdjustCompareDetailinsAllResponse AdjustCompareDetailinsAll(AdjustCompareDetail req);
    public Boolean AdjustCompareDetailupdByCon(AdjustCompareDetail req);
    public Boolean AdjustCompareDetailupdDelete(AdjustCompareDetailupdDeleteReq req);
}
