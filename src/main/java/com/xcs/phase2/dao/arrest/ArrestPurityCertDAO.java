package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestPurityCert;
import com.xcs.phase2.request.arrest.ArrestPurityCertgetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestPurityCertgetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestPurityCertupdByConReq;
import com.xcs.phase2.request.arrest.ArrestPurityCertupdDeleteReq;

import java.util.List;


public interface ArrestPurityCertDAO {

	public List<ArrestPurityCert> ArrestPurityCertgetByKeyword(ArrestPurityCertgetByKeywordReq req);
	public List<ArrestPurityCert> ArrestPurityCertgetByConAdv(ArrestPurityCertgetByConAdvReq req);
	public Boolean ArrestPurityCertupdByCon(List<ArrestPurityCertupdByConReq> req);
	public Boolean ArrestPurityCertupdDelete(List<ArrestPurityCertupdDeleteReq> req);
}
