package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveScience;
import com.xcs.phase2.request.prove.ProveSciencegetByConReq;
import com.xcs.phase2.request.prove.ProveScienceupdDeleteReq;
import com.xcs.phase2.response.prove.ProveScienceinsAllResponse;

import java.util.List;

public interface ProveScienceDAO {

    public List<ProveScience> ProveSciencegetByCon(ProveSciencegetByConReq req);
    public ProveScienceinsAllResponse ProveScienceinsAll(ProveScience req);
    public Boolean ProveScienceupdByCon(ProveScience req);
    public Boolean ProveScienceupdDelete(ProveScienceupdDeleteReq req);
}
