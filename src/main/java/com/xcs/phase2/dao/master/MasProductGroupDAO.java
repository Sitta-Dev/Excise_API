package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasProductGroup;
import com.xcs.phase2.request.master.MasProductGroupgetByConReq;
import com.xcs.phase2.request.master.MasProductGroupupdDeleteReq;
import com.xcs.phase2.response.master.MasProductGroupinsAllResponse;

import java.util.List;

public interface MasProductGroupDAO {

    public List<MasProductGroup> MasProductGroupgetByCon(MasProductGroupgetByConReq req);
    public MasProductGroupinsAllResponse MasProductGroupinsAll(MasProductGroup req);
    public Boolean MasProductGroupupdAll(MasProductGroup req);
    public Boolean MasProductGroupupdDelete(MasProductGroupupdDeleteReq req);
}
