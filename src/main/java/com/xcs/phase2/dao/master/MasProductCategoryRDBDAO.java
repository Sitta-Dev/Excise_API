package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductCategoryRDB;
import com.xcs.phase2.request.master.MasProductCategoryRDBgetByConReq;

import java.util.List;

public interface MasProductCategoryRDBDAO  {

    public List<MasProductCategoryRDB> MasProductCategoryRDBgetByCon(MasProductCategoryRDBgetByConReq req);
}
