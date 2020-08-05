package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareStaff;
import com.xcs.phase2.request.compare.CompareDetailStaffupdDeleteReq;
import com.xcs.phase2.response.compare.CompareDetailStaffinsAllResponse;

import java.util.List;

public interface CompareDetailStaffDAO {

    public CompareDetailStaffinsAllResponse CompareDetailStaffinsAll(List<CompareStaff> req);
    public Boolean CompareDetailStaffupdByCon(List<CompareStaff> req);
    public Boolean CompareDetailStaffupdDelete(List<CompareDetailStaffupdDeleteReq> req);
}
