package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductMapping;
import com.xcs.phase2.model.master.ProductMapping;
import com.xcs.phase2.request.master.MasProductMappinggetByConAdvReq;
import com.xcs.phase2.request.master.MasProductMappinggetByConReq;
import com.xcs.phase2.request.master.MasProductMappinggetByKeywordReq;
import com.xcs.phase2.request.master.MasProductMappingupdDeleteReq;
import com.xcs.phase2.response.master.MasProductMappinginsAllResponse;

import java.util.List;

public interface MasProductMappingDAO {

	public List<ProductMapping> MasProductOnlygetByKeyword(MasProductMappinggetByKeywordReq req);
    public List<ProductMapping> MasProductMappinggetByKeyword(MasProductMappinggetByKeywordReq req);
    public List<ProductMapping> MasProductOnlygetByConAdv(MasProductMappinggetByConAdvReq req);
    public List<ProductMapping> MasProductMappinggetByConAdv(MasProductMappinggetByConAdvReq req);
    public MasProductMapping MasProductMappinggetByCon(MasProductMappinggetByConReq req);
    public MasProductMappinginsAllResponse MasProductMappinginsAll(MasProductMapping req);
    public Boolean MasProductMappingupdAll(MasProductMapping req);
    public Boolean MasProductMappingupdDelete(MasProductMappingupdDeleteReq req);
}
