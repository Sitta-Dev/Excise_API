package com.xcs.phase2.dao.evidencein;


import com.xcs.phase2.model.evidencein.TransactionRunningItem;
import com.xcs.phase2.request.evidencein.TransactionRunningItemgetByConReq;
import com.xcs.phase2.request.evidencein.TransactionRunningItemupdByConReq;
import com.xcs.phase2.response.evidencein.TransactionRunningIteminsAllResponse;

import java.util.List;

public interface TransactionRunningItemDAO {

    public List<TransactionRunningItem> TransactionRunningItemgetByCon(TransactionRunningItemgetByConReq req);
    public TransactionRunningIteminsAllResponse TransactionRunningIteminsAll(TransactionRunningItem req);
    public Boolean TransactionRunningItemupdByCon(TransactionRunningItemupdByConReq req);
}
