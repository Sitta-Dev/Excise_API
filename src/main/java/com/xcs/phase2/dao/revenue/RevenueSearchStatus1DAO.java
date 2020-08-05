package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.model.revenue.RevenueSearchStatus1;
import com.xcs.phase2.request.revenue.RevenueSearchStatus1Req;

import java.util.List;

public interface RevenueSearchStatus1DAO {

    public List<RevenueSearchStatus1> RevenueSearchStatus1(RevenueSearchStatus1Req req);
}
