package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveLawbreaker;
import com.xcs.phase2.request.prove.ProveLawbreakergetByConReq;
import com.xcs.phase2.request.prove.ProveLawbreakerupdDeleteReq;
import com.xcs.phase2.response.prove.ProveLawbreakerinsAllResponse;

import java.util.List;

public interface ProveLawbreakerDAO {

    public List<ProveLawbreaker> ProveLawbreakergetByCon(ProveLawbreakergetByConReq req);
    public ProveLawbreakerinsAllResponse ProveLawbreakerinsAll(ProveLawbreaker req);
    public Boolean ProveLawbreakerupdByCon(ProveLawbreaker req);
    public Boolean ProveLawbreakerupdDelete(ProveLawbreakerupdDeleteReq req);
}
