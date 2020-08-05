package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.model.revenue.NewRevenueList;
import com.xcs.phase2.model.revenue.RevenueList;
import com.xcs.phase2.request.revenue.*;
import com.xcs.phase2.response.revenue.RevenueinsAllResponse;

import java.util.List;

public interface RevenueListDAO {

    public List<RevenueList> RevenuegetByKeyword(RevenuegetByKeywordReq req);
    public List<RevenueList> RevenuegetByConAdv(RevenuegetByConAdvReq req);
    public RevenueList RevenuegetByCon(RevenuegetByConReq req);
    public RevenueinsAllResponse RevenueinsAll(RevenueList req);
    public Boolean RevenueupdByCon(RevenueList req);
    public Boolean RevenueupdDelete(RevenueupdDeleteReq req);
    public Boolean RevenuePaymentupdByCon(RevenuePaymentupdByConReq req);

    public List<NewRevenueList> RevenueListgetByKeyword(RevenuegetByKeywordReq req);
    public List<NewRevenueList> RevenueListgetByConAdv(RevenuegetByConAdvReq req);

}
