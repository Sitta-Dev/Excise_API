package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.model.adjust.AdjustComparePayment;
import com.xcs.phase2.request.adjust.AdjustComparePaymentupdDeleteReq;
import com.xcs.phase2.response.adjust.AdjustComparePaymentinsAllResponse;

import java.util.List;

public interface AdjustComparePaymentDAO {

    public AdjustComparePaymentinsAllResponse AdjustComparePaymentinsAll(List<AdjustComparePayment> req);
    public Boolean AdjustComparePaymentupdDelete(List<AdjustComparePaymentupdDeleteReq> req);
}
