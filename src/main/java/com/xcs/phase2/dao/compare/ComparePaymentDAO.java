package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.ComparePayment;
import com.xcs.phase2.request.compare.ComparePaymentupdDeleteReq;
import com.xcs.phase2.response.compare.ComparePaymentinsAllResponse;

import java.util.List;

public interface ComparePaymentDAO {

    public ComparePaymentinsAllResponse ComparePaymentinsAll(List<ComparePayment> req);
    public Boolean ComparePaymentupdDelete(List<ComparePaymentupdDeleteReq> req);
}
