package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.model.adjust.AdjustCompareDetailPayment;
import com.xcs.phase2.request.adjust.AdjustCompareDetailPaymentupdDeleteReq;
import com.xcs.phase2.response.adjust.AdjustCompareDetailPaymentinsAllResponse;

import java.util.List;

public interface AdjustCompareDetailPaymentDAO {

    public AdjustCompareDetailPaymentinsAllResponse AdjustCompareDetailPaymentinsAll(List<AdjustCompareDetailPayment> req);
    public Boolean AdjustCompareDetailPaymentupdByCon(List<AdjustCompareDetailPayment> req);
    public Boolean AdjustCompareDetailPaymentupdDelete(List<AdjustCompareDetailPaymentupdDeleteReq> req);
}
