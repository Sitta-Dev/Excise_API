package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareArrestIndictment;
import com.xcs.phase2.model.compare.CompareArrestgetByIndictment;
import com.xcs.phase2.request.compare.CompareArrestgetByIndictmentIDReq;

import java.util.List;

public interface CompareArrestIndictmentDAO {

    public List<CompareArrestIndictment> CompareArrestgetByIndictmentID(CompareArrestgetByIndictmentIDReq req);
    public CompareArrestgetByIndictment CompareArrestgetByIndictment(CompareArrestgetByIndictmentIDReq req);
}
