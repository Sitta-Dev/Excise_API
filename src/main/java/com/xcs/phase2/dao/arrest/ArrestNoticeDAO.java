package com.xcs.phase2.dao.arrest;

import com.xcs.phase2.model.arrest.ArrestNotice;
import com.xcs.phase2.request.arrest.ArrestNoticegetByConAdvReq;
import com.xcs.phase2.request.arrest.ArrestNoticegetByKeywordReq;
import com.xcs.phase2.request.arrest.ArrestNoticeupdByConReq;
import com.xcs.phase2.request.arrest.ArrestNoticeupdDeleteReq;

import java.util.List;


public interface ArrestNoticeDAO {

	public List<ArrestNotice> ArrestNoticegetByKeyword(ArrestNoticegetByKeywordReq req);
	public List<ArrestNotice> ArrestNoticegetByConAdv(ArrestNoticegetByConAdvReq req);
	public Boolean ArrestNoticeupdByCon(List<ArrestNoticeupdByConReq> req);
	public Boolean ArrestNoticeupdDelete(List<ArrestNoticeupdDeleteReq> req);
}
