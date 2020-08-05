package com.xcs.phase2.dao.notice;


import com.xcs.phase2.model.notice.Notice;
import com.xcs.phase2.request.notice.NoticegetByConReq;
import com.xcs.phase2.request.notice.NoticeupdDeleteReq;
import com.xcs.phase2.response.notice.NoticeinsAllResponse;

public interface NoticeDAO {

    public Notice NoticegetByCon(NoticegetByConReq req);
    public NoticeinsAllResponse NoticeinsAll(Notice req);
    public Boolean NoticeupdByCon(Notice req);
    public Boolean NoticeupdDelete(NoticeupdDeleteReq req);
}
