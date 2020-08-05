package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductPRC;
import com.xcs.phase2.request.master.MasProductPRCgetByConReq;

import java.util.List;

public interface MasProductPRCDAO {

    public List<MasProductPRC> MasProductPRCgetByCon(MasProductPRCgetByConReq req);
}
