package com.xcs.phase2.dao.notice;


import com.xcs.phase2.model.notice.NoticeProduct;
import com.xcs.phase2.request.notice.NoticeProductupdDeleteReq;
import com.xcs.phase2.response.notice.NoticeProductinsAllResponse;

public interface NoticeProductDAO {

    public NoticeProductinsAllResponse NoticeProductinsAll(NoticeProduct req);
    public Boolean NoticeProductupdByCon(NoticeProduct req);
    public Boolean NoticeProductupdDelete(NoticeProductupdDeleteReq req);
}
