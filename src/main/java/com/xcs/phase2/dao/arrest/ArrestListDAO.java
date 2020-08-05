package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestLawbreakerPerson;
import com.xcs.phase2.model.arrest.ArrestList;
import com.xcs.phase2.model.arrest.ArrestPerson;
import com.xcs.phase2.request.arrest.ArrestListgetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestListgetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestgetByPersonIdReq;
import com.xcs.phase2.request.arrest.LawbreakerRelationshipgetByPersonIdReq;

import java.util.List;

public interface ArrestListDAO {

	public List<ArrestList> ArrestListgetByKeyword(ArrestListgetByKeywordReq req);
	public List<ArrestList> ArrestListgetByConAdv(ArrestListgetByConAdvReq req);
	public List<ArrestPerson> ArrestgetByPersonId(ArrestgetByPersonIdReq req);
	public List<ArrestLawbreakerPerson> LawbreakerRelationshipgetByPersonId(LawbreakerRelationshipgetByPersonIdReq req);
}
