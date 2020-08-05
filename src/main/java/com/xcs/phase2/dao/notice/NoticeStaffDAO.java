package com.xcs.phase2.dao.notice;


import com.xcs.phase2.model.notice.NoticeStaff;
import com.xcs.phase2.request.notice.NoticeStaffupdDeleteReq;
import com.xcs.phase2.response.notice.NoticeStaffinsAllResponse;

public interface NoticeStaffDAO {

    public NoticeStaffinsAllResponse NoticeStaffinsAll(NoticeStaff req);
    public Boolean NoticeStaffupdByCon(NoticeStaff req);
    public Boolean NoticeStaffupdDelete(NoticeStaffupdDeleteReq req);
}
