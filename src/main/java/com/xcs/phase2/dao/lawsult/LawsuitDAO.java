package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.model.lawsult.Lawsuit;
import com.xcs.phase2.model.other.Running;
import com.xcs.phase2.request.other.RunningNoReq;
import com.xcs.phase2.request.lawsult.LawsuitVerifyLawsuitNoReq;
import com.xcs.phase2.request.lawsult.LawsuitgetByConReq;
import com.xcs.phase2.request.lawsult.LawsuitupdDeleteReq;
import com.xcs.phase2.response.lawsult.LawsuitinsAllResponse;

import java.util.List;



public interface LawsuitDAO {
	
	public LawsuitinsAllResponse LawsuitinsAll(Lawsuit req);
	public Boolean LawsuitupdAll(Lawsuit req);
	public Boolean LawsuitupdDelete(LawsuitupdDeleteReq req);
	public Lawsuit LawsuitgetByCon(LawsuitgetByConReq req);
	public List<Lawsuit> LawsuitVerifyLawsuitNo(LawsuitVerifyLawsuitNoReq req);
}

