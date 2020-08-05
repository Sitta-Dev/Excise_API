package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestIndictmentDetail;
import com.xcs.phase2.request.arrest.ArrestIndictmentDetailupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestIndictmentDetailinsAllResponse;

import java.util.List;



public interface ArrestIndictmentDetailDAO {

	public ArrestIndictmentDetailinsAllResponse ArrestIndictmentDetailinsAll(List<ArrestIndictmentDetail> req);
	public Boolean ArrestIndictmentDetailupdDelete(List<ArrestIndictmentDetailupdDeleteReq> req);
}
