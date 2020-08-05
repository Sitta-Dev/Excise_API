package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.model.adjust.AdjustCompareList;
import com.xcs.phase2.request.adjust.AdjustCompareListgetByConAdvReq;
import com.xcs.phase2.request.adjust.AdjustCompareListgetByKeywordReq;

import java.util.List;

public interface AdjustCompareListDAO {

    public List<AdjustCompareList> AdjustCompareListgetByKeyword(AdjustCompareListgetByKeywordReq req);
    public List<AdjustCompareList> AdjustCompareListgetByConAdv(AdjustCompareListgetByConAdvReq req);
}
