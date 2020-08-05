package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.model.revenue.RevenueCompareDetail;
import com.xcs.phase2.request.revenue.RevenueCompareDetailReceiptupdByConReq;
import com.xcs.phase2.request.revenue.RevenueCompareDetailReceiptupdDeleteReq;
import com.xcs.phase2.request.revenue.RevenueComparegetByCompareReceiptIDReq;

import java.util.List;

public interface RevenueCompareDetailDAO {

    public List<RevenueCompareDetail> RevenueComparegetByCompareReceiptID(RevenueComparegetByCompareReceiptIDReq req);
    public Boolean RevenueCompareDetailReceiptupdByCon(RevenueCompareDetailReceiptupdByConReq Req);
    public Boolean RevenueCompareDetailReceiptupdDelete(RevenueCompareDetailReceiptupdDeleteReq Req);
}
