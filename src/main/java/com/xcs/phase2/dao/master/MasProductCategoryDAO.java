package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductCategory;
import com.xcs.phase2.request.master.MasProductCategorygetByConReq;
import com.xcs.phase2.request.master.MasProductCategoryupdDeleteReq;
import com.xcs.phase2.response.master.MasProductCategoryinsAllResponse;

import java.util.List;

public interface MasProductCategoryDAO {

    public List<MasProductCategory> MasProductCategorygetByCon(MasProductCategorygetByConReq req);
    public MasProductCategoryinsAllResponse MasProductCategoryinsAll(MasProductCategory req);
    public Boolean MasProductCategoryupdAll(MasProductCategory req);
    public Boolean MasProductCategoryupdDelete(MasProductCategoryupdDeleteReq req);
}
