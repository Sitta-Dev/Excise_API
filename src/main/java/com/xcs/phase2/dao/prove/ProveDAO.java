package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.Prove;
import com.xcs.phase2.model.prove.ProveCompare;
import com.xcs.phase2.request.prove.ProveComparegetByProveIDReq;
import com.xcs.phase2.request.prove.ProveVerifyProveNoReq;
import com.xcs.phase2.request.prove.ProvegetByConReq;
import com.xcs.phase2.request.prove.ProveupdDeleteReq;
import com.xcs.phase2.response.prove.CourtJudgmentResponse;
import com.xcs.phase2.response.prove.ProveinsAllResponse;

import java.util.List;

public interface ProveDAO {

    public Prove ProvegetByCon(ProvegetByConReq req);
    public ProveinsAllResponse ProveinsAll(Prove req);
    public Boolean ProveupdByCon(Prove req);
    public Boolean ProveupdDelete(ProveupdDeleteReq req);
    public List<Prove> ProveVerifyProveNo(ProveVerifyProveNoReq req);
    public ProveCompare ProveComparegetByProveID(ProveComparegetByProveIDReq req);
    public List<CourtJudgmentResponse> ProveVerifyCourtJudgment(ProvegetByConReq req);
}

