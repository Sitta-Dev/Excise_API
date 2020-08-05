package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.model.lawsult.LawsuitList;
import com.xcs.phase2.request.lawsult.LawsuiltListgetByConAdvReq;
import com.xcs.phase2.request.lawsult.LawsuiltListgetByKeywordReq;

import java.util.List;

public interface LawsuitListDAO {

	public List<LawsuitList> LawsuiltListgetByKeyword(LawsuiltListgetByKeywordReq req);
	public List<LawsuitList> LawsuiltListgetByConAdv(LawsuiltListgetByConAdvReq req);
	//public List<LawsuitList> LawsuiltListgetByLawbreaker(LawsuiltListgetByLawbreakerReq req);
}
