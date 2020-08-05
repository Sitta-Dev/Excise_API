package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestMasGuiltbase;
import com.xcs.phase2.model.arrest.NewArrestMasGuiltbase;
import com.xcs.phase2.request.arrest.ArrestMasGuiltbasegetByKeywordReq;

import java.util.List;



public interface ArrestMasGuiltbaseDAO {

	public List<ArrestMasGuiltbase> ArrestMasGuiltbasegetByKeyword(ArrestMasGuiltbasegetByKeywordReq req);

	public List<NewArrestMasGuiltbase> NewArrestMasGuiltbasegetByKeyword(ArrestMasGuiltbasegetByKeywordReq req);
}
