package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestProduct;
import com.xcs.phase2.request.arrest.ArrestProductupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestProductinsAllResponse;

import java.util.List;



public interface ArrestProductDAO {

	public ArrestProductinsAllResponse ArrestProductinsAll(List<ArrestProduct> req);
	public Boolean ArrestProductupdByCon(List<ArrestProduct> req);
	public Boolean ArrestProductupdDelete(List<ArrestProductupdDeleteReq> req);
}
