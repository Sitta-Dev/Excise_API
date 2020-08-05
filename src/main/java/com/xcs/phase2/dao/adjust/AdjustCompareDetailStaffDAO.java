package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.model.adjust.AdjustCompareStaff;
import com.xcs.phase2.request.adjust.AdjustCompareDetailStaffupdDeleteReq;
import com.xcs.phase2.response.adjust.AdjustCompareDetailStaffinsAllResponse;

import java.util.List;

public interface AdjustCompareDetailStaffDAO {

    public AdjustCompareDetailStaffinsAllResponse AdjustCompareDetailStaffinsAll(List<AdjustCompareStaff> req);
    public Boolean AdjustCompareDetailStaffupdByCon(List<AdjustCompareStaff> req);
    public Boolean AdjustCompareDetailStaffupdDelete(List<AdjustCompareDetailStaffupdDeleteReq> req);
}
