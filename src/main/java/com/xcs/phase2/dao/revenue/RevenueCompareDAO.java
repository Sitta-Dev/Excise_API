package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.model.revenue.NewRevenueCourt;
import com.xcs.phase2.model.revenue.RevenueCompare;
import com.xcs.phase2.request.revenue.*;

import java.util.List;

public interface RevenueCompareDAO {

    public List<RevenueCompare> RevenueComparegetByCreate(RevenueComparegetByCreateReq req);

    public boolean RevenueCourtupdStatus(RevenueCourtupdStatusReq req);

    public boolean RevenueCompareStatus(RevenueCompareStatusReq req);

    public boolean RevenueReturnupdREFno(RevenueReturnupdREFnoReq req);

    public List<RevenueCompare> RevenueComparegetByCon(RevenueComparegetByConReq req);

    public List<NewRevenueCourt> RevenueCourtgetByCon(RevenueCourtgetByConReq req);


}
