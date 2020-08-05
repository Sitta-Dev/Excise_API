package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.Compare;
import com.xcs.phase2.request.compare.CompareVerifyCompareNoReq;
import com.xcs.phase2.request.compare.ComparegetByConReq;
import com.xcs.phase2.request.compare.CompareupdDeleteReq;
import com.xcs.phase2.response.compare.CompareinsAllResponse;

import java.util.List;

public interface CompareDAO {

    public Compare ComparegetByCon(ComparegetByConReq req);
    public CompareinsAllResponse CompareinsAll(Compare req);
    public Boolean CompareupdByCon(Compare req);
    public Boolean CompareupdDelete(CompareupdDeleteReq req);
    public List<Compare> CompareVerifyCompareNo(CompareVerifyCompareNoReq req);
}
