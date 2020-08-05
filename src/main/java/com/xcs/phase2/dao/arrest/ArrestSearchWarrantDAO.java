package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestSearchWarrant;
import com.xcs.phase2.request.arrest.ArrestSearchWarrantgetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestSearchWarrantgetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestSearchWarrantupdByConReq;
import com.xcs.phase2.request.arrest.ArrestSearchWarrantupdDeleteReq;

import java.util.List;



public interface ArrestSearchWarrantDAO {

	public List<ArrestSearchWarrant> ArrestSearchWarrantgetByKeyword(ArrestSearchWarrantgetByKeywordReq req);
	public List<ArrestSearchWarrant> ArrestSearchWarrantgetByConAdv(ArrestSearchWarrantgetByConAdvReq req);
	public Boolean ArrestSearchWarrantupdByCon(List<ArrestSearchWarrantupdByConReq> req);
	public Boolean ArrestSearchWarrantupdDelete(List<ArrestSearchWarrantupdDeleteReq> req);
}
