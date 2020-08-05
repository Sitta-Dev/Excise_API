package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveArrestIndictmentProductWeb;
import com.xcs.phase2.request.prove.ProveArrestIndictmentProductWebgetByConReq;

import java.util.List;

public interface ProveArrestIndictmentProductWebDAO {

    public List<ProveArrestIndictmentProductWeb> ProveArrestIndictmentProductWebgetByCon(ProveArrestIndictmentProductWebgetByConReq req);
}
