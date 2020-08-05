package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.TransactionRunning;
import com.xcs.phase2.request.compare.TransactionRunninggetByConReq;
import com.xcs.phase2.request.compare.TransactionRunningupdByConReq;

import java.util.List;

public interface TransactionRunningDAO {

    public List<TransactionRunning> TransactionRunninggetByCon(TransactionRunninggetByConReq req);
    public Boolean TransactionRunninginsAll(TransactionRunning req);
    public Boolean TransactionRunningupdByCon(TransactionRunningupdByConReq req);
    public TransactionRunning getTransactionRunninggetByCon(TransactionRunninggetByConReq req);
}
