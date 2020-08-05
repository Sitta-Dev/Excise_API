package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveList;
import com.xcs.phase2.request.prove.ProveListgetByConAdvReq;
import com.xcs.phase2.request.prove.ProveListgetByKeywordReq;

import java.util.List;

public interface ProveListDAO {

    public List<ProveList> ProveListgetByKeyword(ProveListgetByKeywordReq req);
    public List<ProveList> ProveListgetByConAdv(ProveListgetByConAdvReq req);
}
