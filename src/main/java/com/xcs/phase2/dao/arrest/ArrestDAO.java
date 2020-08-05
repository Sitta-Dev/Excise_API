package com.xcs.phase2.dao.arrest;


import com.xcs.phase2.model.arrest.Arrest;
import com.xcs.phase2.request.arrest.ArrestgetByConReq;
import com.xcs.phase2.request.arrest.ArrestupdDeleteReq;
import com.xcs.phase2.response.arrest.ArrestinsAllResponse;

public interface ArrestDAO {

	public Arrest ArrestgetByCon(ArrestgetByConReq req);
	public ArrestinsAllResponse ArrestinsAll(Arrest req);
	public Boolean ArrestupdByCon(Arrest req);
	public Boolean ArrestupdDelete(ArrestupdDeleteReq req);
}
