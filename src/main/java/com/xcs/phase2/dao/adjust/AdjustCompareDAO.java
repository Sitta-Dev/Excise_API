package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.model.adjust.AdjustCompare;
import com.xcs.phase2.request.adjust.AdjustComparegetByConReq;

public interface AdjustCompareDAO {

    public AdjustCompare AdjustComparegetByCon(AdjustComparegetByConReq req);
}
