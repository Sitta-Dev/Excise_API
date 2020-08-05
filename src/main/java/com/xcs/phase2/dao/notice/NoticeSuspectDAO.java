package com.xcs.phase2.dao.notice;


import com.xcs.phase2.model.notice.NoticeSuspect;
import com.xcs.phase2.request.notice.NoticeSuspectupdDeleteReq;
import com.xcs.phase2.response.notice.NoticeSupectinsAllResponse;

public interface NoticeSuspectDAO {

    public NoticeSupectinsAllResponse NoticeSupectinsAll(NoticeSuspect req);
    public Boolean NoticeSuspectupdDelete(NoticeSuspectupdDeleteReq req);
}
