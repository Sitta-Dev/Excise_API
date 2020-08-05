package com.xcs.phase2.dao.arrest;


import com.xcs.phase2.model.arrest.ArrestIndictmentProduct;
import com.xcs.phase2.request.arrest.ArrestIndictmentProductupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestIndictmentProductinsAllResponse;

import java.util.List;



public interface ArrestIndictmentProductDAO {

	public ArrestIndictmentProductinsAllResponse ArrestIndictmentProductinsAll(List<ArrestIndictmentProduct> req);
	public Boolean ArrestIndictmentProductupdByCon(List<ArrestIndictmentProduct> req);
	public Boolean ArrestIndictmentProductupdDelete(List<ArrestIndictmentProductupdDeleteReq> req);
}
