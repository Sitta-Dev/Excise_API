package com.xcs.phase2.dao.lawsult;


import com.xcs.phase2.request.lawsult.LawsuitMistreatNoDeleteReq;
import com.xcs.phase2.request.lawsult.LawsuitMistreatNoUpdateReq;
import com.xcs.phase2.response.lawsult.LawsuitMistreatNoResponse;

public interface LawsuitMistreatNoDAO {

	public LawsuitMistreatNoResponse LawsuitMistreatNoUpdate(LawsuitMistreatNoUpdateReq req);
	public LawsuitMistreatNoResponse LawsuitMistreatNoDelete(LawsuitMistreatNoDeleteReq req);
}
