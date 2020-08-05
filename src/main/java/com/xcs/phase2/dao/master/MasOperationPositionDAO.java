package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasOperationPosition;
import com.xcs.phase2.request.master.MasOperationPositionGetByConReq;

import java.util.List;

public interface MasOperationPositionDAO {

    public List<MasOperationPosition> MasOperationPositionGetByCon(MasOperationPositionGetByConReq req);
}
