package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.model.adjust.AdjustCompareArrest;
import com.xcs.phase2.model.adjust.AdjustCompareArrestNew;
import com.xcs.phase2.request.adjust.AdjustCompareArrestgetByIndictmentIDReq;

import java.util.List;

public interface AdjustCompareArrestDAO {

    public List<AdjustCompareArrest> AdjustCompareArrestgetByIndictmentID(AdjustCompareArrestgetByIndictmentIDReq req);
    public List<AdjustCompareArrestNew> AdjustCompareArrestgetByIndictmentIDNew(AdjustCompareArrestgetByIndictmentIDReq req);
}
