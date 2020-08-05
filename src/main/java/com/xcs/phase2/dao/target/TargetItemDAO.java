package com.xcs.phase2.dao.target;


import com.xcs.phase2.model.target.TargetItem;
import com.xcs.phase2.response.target.TargetIteminsAllResponse;

import java.util.List;

public interface TargetItemDAO {

    public TargetIteminsAllResponse TargetIteminsAll(List<TargetItem> req);
    public Boolean TargetItemupdByCon(List<TargetItem> req);
}
