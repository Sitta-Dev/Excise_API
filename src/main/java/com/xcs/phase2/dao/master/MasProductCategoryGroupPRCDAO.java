package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductCategoryGroupPRC;
import com.xcs.phase2.request.master.MasProductCategoryGroupPRCgetByConReq;

import java.util.List;

public interface MasProductCategoryGroupPRCDAO {

    public List<MasProductCategoryGroupPRC> MasProductCategoryGroupPRCgetByCon(MasProductCategoryGroupPRCgetByConReq req);
}
