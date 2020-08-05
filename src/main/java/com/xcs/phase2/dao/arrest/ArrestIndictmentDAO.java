package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestIndictment;
import com.xcs.phase2.request.arrest.ArrestIndictmentupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestIndictmentinsAllResponse;

import java.util.List;



public interface ArrestIndictmentDAO {

	public ArrestIndictmentinsAllResponse ArrestIndictmentinsAll(List<ArrestIndictment> req);
	public Boolean ArrestIndictmentupdByCon(List<ArrestIndictment> req);
	public Boolean ArrestIndictmentupdDelete(List<ArrestIndictmentupdDeleteReq> req);
}
