package com.xcs.phase2.dao.revenue;


import com.xcs.phase2.model.revenue.RevenueIncPayment;
import com.xcs.phase2.request.revenue.IncPaymentListGetByConReq;
import com.xcs.phase2.request.revenue.IncPaymentListUpdDeleteReq;
import com.xcs.phase2.response.revenue.IncPaymentListinsAllResponse;

import java.util.List;

public interface IncPaymentListDAO {

    public IncPaymentListinsAllResponse IncPaymentListinsAll(List<RevenueIncPayment> req);

    public List<RevenueIncPayment> IncPaymentListGetByCon(IncPaymentListGetByConReq req);

    public boolean IncPaymentListUpdDelete(IncPaymentListUpdDeleteReq req);
}
