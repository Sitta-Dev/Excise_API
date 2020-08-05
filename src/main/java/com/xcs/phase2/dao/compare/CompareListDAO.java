package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareList;
import com.xcs.phase2.request.compare.CompareListgetByConAdvReq;
import com.xcs.phase2.request.compare.CompareListgetByKeywordReq;

import java.util.List;

public interface CompareListDAO {

    public List<CompareList> CompareListgetByKeyword(CompareListgetByKeywordReq req);
    public List<CompareList> CompareListgetByConAdv(CompareListgetByConAdvReq req);
}
