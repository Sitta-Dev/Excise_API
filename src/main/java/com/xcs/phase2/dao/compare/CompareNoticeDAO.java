package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareNotice;
import com.xcs.phase2.request.compare.CompareNoticegetByArrestIDReq;

import java.util.List;

public interface CompareNoticeDAO {

    public List<CompareNotice> CompareNoticegetByArrestID(CompareNoticegetByArrestIDReq req);
}
