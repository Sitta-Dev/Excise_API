package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveProduct;
import com.xcs.phase2.request.prove.ProveProductgetByProductIdReq;
import com.xcs.phase2.request.prove.ProveProductgetByProveIdReq;
import com.xcs.phase2.request.prove.ProveProductupdDeleteReq;
import com.xcs.phase2.response.prove.ProveProductinsAllResponse;

import java.util.List;

public interface ProveProductDAO {

    public ProveProduct ProveProductgetByProductId(ProveProductgetByProductIdReq req);
    public List<ProveProduct> ProveProductgetByProveId(ProveProductgetByProveIdReq req);
    public ProveProductinsAllResponse ProveProductinsAll(ProveProduct req);
    public Boolean ProveProductupdByCon(ProveProduct req);
    public Boolean ProveProductupdDelete(ProveProductupdDeleteReq req);
}
