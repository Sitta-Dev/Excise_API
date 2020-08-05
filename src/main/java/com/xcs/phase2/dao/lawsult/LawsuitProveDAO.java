package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.model.lawsult.LawsuitProve;
import com.xcs.phase2.request.lawsult.LawsuiltProvegetByLawsuitIDReq;

public interface LawsuitProveDAO {

	public LawsuitProve LawsuiltProvegetByLawsuitID(LawsuiltProvegetByLawsuitIDReq req);
}
