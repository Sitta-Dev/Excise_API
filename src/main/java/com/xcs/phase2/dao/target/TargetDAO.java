package com.xcs.phase2.dao.target;


import com.xcs.phase2.model.target.Target;
import com.xcs.phase2.model.target.TargetgetByCon;
import com.xcs.phase2.request.target.IsSentTargetupdByConReq;
import com.xcs.phase2.request.target.TargetgetByConReq;
import com.xcs.phase2.request.target.TargetupdByConReq;
import com.xcs.phase2.request.target.TargetupdDeleteReq;
import com.xcs.phase2.response.target.TargetinsAllResponse;

import java.util.List;

public interface TargetDAO {

    public List<TargetgetByCon> TargetgetByCon(TargetgetByConReq req);
    public TargetinsAllResponse TargetinsAll(Target req);
    public Boolean TargetupdByCon(List<TargetupdByConReq> req);
    public Boolean IsSentTargetupdByCon(IsSentTargetupdByConReq req);
    public Boolean TargetupdDelete(TargetupdDeleteReq req);
}
