package com.xcs.phase2.dao.target;


import com.xcs.phase2.model.target.TargetItemDetail;
import com.xcs.phase2.response.target.TargetItemDeatailinsAllResponse;

import java.util.List;

public interface TargetItemDetailDAO {

    public TargetItemDeatailinsAllResponse TargetItemDeatailinsAll(List<TargetItemDetail> req);
    public Boolean TargetItemDetailupdByCon(List<TargetItemDetail> req);
}
