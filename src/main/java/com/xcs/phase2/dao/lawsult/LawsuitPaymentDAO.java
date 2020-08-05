package com.xcs.phase2.dao.lawsult;

import com.xcs.phase2.model.lawsult.LawsuitPayment;
import com.xcs.phase2.request.lawsult.LawsuitPaymentgetByConReq;
import com.xcs.phase2.request.lawsult.LawsuitPaymentupdDeleteReq;
import com.xcs.phase2.response.lawsult.LawsuitPaymentinsAllResposne;

import java.util.List;

public interface LawsuitPaymentDAO {

	public LawsuitPaymentinsAllResposne LawsuitPaymentinsAll(List<LawsuitPayment> req);
	public Boolean LawsuitPaymentupdDelete(List<LawsuitPaymentupdDeleteReq> req);

	public List<LawsuitPayment> LawsuitPaymentgetByCon(LawsuitPaymentgetByConReq req);
}
