package com.xcs.phase2.dao.lawsult;


import com.xcs.phase2.model.lawsult.LawsuitStaff;
import com.xcs.phase2.request.lawsult.LawsuitStaffupdDeleteReq;
import com.xcs.phase2.response.lawsult.LawsuitStaffinsAllResponse;

import java.util.List;

public interface LawsuitStaffDAO {

	public LawsuitStaffinsAllResponse LawsuitStaffinsAll(List<LawsuitStaff> req);
	public Boolean LawsuitStaffUpdate(List<LawsuitStaff> req);
	public Boolean LawsuitStaffupdDelete(List<LawsuitStaffupdDeleteReq> req);
}
