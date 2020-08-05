package com.xcs.phase2.dao.uac;

import com.xcs.phase2.model.uac.EdOffice;
import com.xcs.phase2.request.uac.EDOfficeAreagetbyConReq;
import com.xcs.phase2.request.uac.EDOfficeBranchgetbyConReq;
import com.xcs.phase2.request.uac.EDOfficeDepartmentgetbyConReq;

import java.util.List;

public interface EdOfficeDAO {

    public List<EdOffice> EDOfficeDepartmentgetbyCon(EDOfficeDepartmentgetbyConReq req);
    public List<EdOffice> EDOfficeAreagetbyCon(EDOfficeAreagetbyConReq req);
    public List<EdOffice> EDOfficeBranchgetbyCon(EDOfficeBranchgetbyConReq req);
    public List<EdOffice> EDOfficeDepartmentgetAll();
}
