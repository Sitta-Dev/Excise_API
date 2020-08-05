package com.xcs.phase2.dao.target;


import com.xcs.phase2.model.target.TargetList;
import com.xcs.phase2.request.target.TargetListgetByConAdvReq;
import com.xcs.phase2.request.target.TargetListgetByKeywordReq;

import java.util.List;

public interface TargetListDAO {

    public List<TargetList> TargetListgetByKeyword(TargetListgetByKeywordReq req);
    public List<TargetList> TargetListgetByConAdv(TargetListgetByConAdvReq req);
}
