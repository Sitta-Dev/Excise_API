package com.xcs.phase2.dao.prove;


import com.xcs.phase2.model.prove.ProveStaff;
import com.xcs.phase2.request.prove.ProveStaffgetByConReq;
import com.xcs.phase2.request.prove.ProveStaffupdDeleteReq;
import com.xcs.phase2.response.prove.ProveStaffinsAllResponse;

import java.util.List;

public interface ProveStaffDAO {

    public List<ProveStaff> ProveStaffgetByCon(ProveStaffgetByConReq req);
    public ProveStaffinsAllResponse ProveStaffinsAll(ProveStaff req);
    public Boolean ProveStaffupdByCon(ProveStaff req);
    public Boolean ProveStaffupdDelete(ProveStaffupdDeleteReq req);
}
