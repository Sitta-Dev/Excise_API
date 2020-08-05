package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.NewProveArrestIndictment;
import com.xcs.phase2.model.prove.ProveArrestIndictmentProduct;
import com.xcs.phase2.request.prove.ProveArrestIndictmentProductgetByConReq;

import java.util.List;

public interface ProveArrestIndictmentProductDAO {

    public List<ProveArrestIndictmentProduct> ProveArrestIndictmentProductgetByCon(ProveArrestIndictmentProductgetByConReq req);

    public NewProveArrestIndictment NewProveArrestIndictmentProductgetByCon(ProveArrestIndictmentProductgetByConReq req);
}
