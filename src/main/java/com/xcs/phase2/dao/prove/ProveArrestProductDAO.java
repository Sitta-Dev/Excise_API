package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveArrestProduct;
import com.xcs.phase2.request.prove.ProveArrestProductgetByConReq;

public interface ProveArrestProductDAO {

    public ProveArrestProduct ProveArrestProductgetByCon(ProveArrestProductgetByConReq req);
}
