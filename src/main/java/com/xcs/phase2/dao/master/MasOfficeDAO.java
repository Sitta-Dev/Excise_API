package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasOffice;
import com.xcs.phase2.request.master.MasOfficegetByConReq;
import com.xcs.phase2.response.master.MasOfficeResponse;

import java.util.List;

public interface MasOfficeDAO {

    public List<MasOffice> MasOfficegetByCon(MasOfficegetByConReq req);
    public MasOfficeResponse MasOfficegetByConList(MasOfficegetByConReq req);
}
