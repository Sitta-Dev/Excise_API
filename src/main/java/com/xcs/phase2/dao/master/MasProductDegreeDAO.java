package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductDegree;
import com.xcs.phase2.request.master.MasProductDegreegetByConReq;

import java.util.List;

public interface MasProductDegreeDAO {

    public List<MasProductDegree> MasProductDegreegetByCon(MasProductDegreegetByConReq req);
}
