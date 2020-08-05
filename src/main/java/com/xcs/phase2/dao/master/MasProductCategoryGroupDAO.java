package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductCategoryGroup;
import com.xcs.phase2.request.master.MasProductCategoryGroupgetByConReq;

import java.util.List;

public interface MasProductCategoryGroupDAO {

    public List<MasProductCategoryGroup> MasProductCategoryGroupgetByCon(MasProductCategoryGroupgetByConReq req);
}
