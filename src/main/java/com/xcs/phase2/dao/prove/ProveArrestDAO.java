package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.NewProveArrest;
import com.xcs.phase2.model.prove.ProveArrest;
import com.xcs.phase2.request.prove.ProveArrestgetByConReq;

import java.util.List;

public interface ProveArrestDAO {

    public List<ProveArrest> ProveArrestgetByCon(ProveArrestgetByConReq req);

    public NewProveArrest NewProveArrestgetByCon(ProveArrestgetByConReq req);
}
