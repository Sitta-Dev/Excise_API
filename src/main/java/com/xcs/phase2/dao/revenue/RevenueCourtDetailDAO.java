package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.model.revenue.NewRevenueCourtDetail;
import com.xcs.phase2.model.revenue.RevenueCourtDetail;
import com.xcs.phase2.request.revenue.RevenueCourtDetailDeleteReq;
import com.xcs.phase2.request.revenue.RevenueCourtDetailgetByConReq;
import com.xcs.phase2.request.revenue.RevenueCourtDetailgetByCreateReq;
import com.xcs.phase2.request.revenue.RevenueCourtDetailupdByConReq;

import java.util.List;

public interface RevenueCourtDetailDAO {

    public List<RevenueCourtDetail> RevenueCourtDetailgetByCon(RevenueCourtDetailgetByConReq req);
    public Boolean RevenueCourtDetailupdByCon(RevenueCourtDetailupdByConReq Req);
    public Boolean RevenueCourtDetailDelete(RevenueCourtDetailDeleteReq Req);

    public List<NewRevenueCourtDetail> RevenueCourtDetailgetByCreate(RevenueCourtDetailgetByCreateReq req);
}
