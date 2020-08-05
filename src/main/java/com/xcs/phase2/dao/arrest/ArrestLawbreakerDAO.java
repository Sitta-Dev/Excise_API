package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestLawbreaker;
import com.xcs.phase2.request.arrest.ArrestLawbreakerupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestLawbreakerinsAllResponse;

import java.util.List;



public interface ArrestLawbreakerDAO {

	public ArrestLawbreakerinsAllResponse ArrestLawbreakerinsAll(List<ArrestLawbreaker> req);
	public Boolean ArrestLawbreakerupdDelete(List<ArrestLawbreakerupdDeleteReq> req);
}
