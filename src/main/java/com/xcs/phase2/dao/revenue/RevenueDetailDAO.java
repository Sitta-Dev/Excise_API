package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.model.revenue.RevenueDetail;
import com.xcs.phase2.request.revenue.RevenueDetailupdDeleteReq;
import com.xcs.phase2.response.revenue.RevenueDetailinsAllResponse;

public interface RevenueDetailDAO {

    public RevenueDetailinsAllResponse RevenueDetailinsAll(RevenueDetail req);
    public Boolean RevenueDetailupdDelete(RevenueDetailupdDeleteReq req);
}
