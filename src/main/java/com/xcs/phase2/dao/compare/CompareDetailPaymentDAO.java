package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareDetailPayment;
import com.xcs.phase2.request.compare.CompareDetailPaymentupdDeleteReq;
import com.xcs.phase2.response.compare.CompareDetailPaymentinsAllResponse;

import java.util.List;

public interface CompareDetailPaymentDAO {

    public CompareDetailPaymentinsAllResponse CompareDetailPaymentinsAll(List<CompareDetailPayment> req);
    public Boolean CompareDetailPaymentupdByCon(List<CompareDetailPayment> req);
    public Boolean CompareDetailPaymentupdDelete(List<CompareDetailPaymentupdDeleteReq> req);
}
