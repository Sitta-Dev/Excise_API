package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestStaff;
import com.xcs.phase2.request.arrest.ArrestStaffupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestStaffinsAllResponse;

import java.util.List;



public interface ArrestStaffDAO {

	public ArrestStaffinsAllResponse ArrestStaffinsAll(List<ArrestStaff> req);
	public Boolean ArrestStaffupdByCon(List<ArrestStaff> req);
	public Boolean ArrestStaffupdDelete(List<ArrestStaffupdDeleteReq> req);
}
