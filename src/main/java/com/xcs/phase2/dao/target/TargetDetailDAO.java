package com.xcs.phase2.dao.target;


import com.xcs.phase2.model.target.TargetDetail;
import com.xcs.phase2.request.target.TargetDetailgetByConReq;

import java.util.List;

public interface TargetDetailDAO {

    public List<TargetDetail> TargetDetailgetByCon(TargetDetailgetByConReq req);
}
