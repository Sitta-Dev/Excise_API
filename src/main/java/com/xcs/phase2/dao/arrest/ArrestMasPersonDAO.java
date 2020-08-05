package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestMasPerson;
import com.xcs.phase2.request.arrest.ArrestMasPersongetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestMasPersongetByKeywordReq;

import java.util.List;



public interface ArrestMasPersonDAO {

	public List<ArrestMasPerson> ArrestMasPersongetByKeyword(ArrestMasPersongetByKeywordReq req);
	public List<ArrestMasPerson> ArrestMasPersongetByConAdv(ArrestMasPersongetByConAdvReq req);
}
