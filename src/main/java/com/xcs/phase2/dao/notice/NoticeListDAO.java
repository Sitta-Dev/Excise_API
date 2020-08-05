package com.xcs.phase2.dao.notice;


import com.xcs.phase2.model.notice.NoticeList;
import com.xcs.phase2.request.notice.NoticeListgetByConAdvReq;
import com.xcs.phase2.request.notice.NoticeListgetByKeywordReq;

import java.util.List;

public interface NoticeListDAO {

    public List<NoticeList> NoticeListgetByKeyword(NoticeListgetByKeywordReq req);
    public List<NoticeList> NoticeListgetByConAdv(NoticeListgetByConAdvReq req);
}
