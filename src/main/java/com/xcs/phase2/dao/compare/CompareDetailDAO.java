package com.xcs.phase2.dao.compare;


import com.xcs.phase2.model.compare.CompareDetail;
import com.xcs.phase2.request.compare.CompareDetailCheckReceriptReq;
import com.xcs.phase2.request.compare.CompareDetailupdDeleteReq;
import com.xcs.phase2.response.compare.CompareDetailCheckReceriptResponse;
import com.xcs.phase2.response.compare.CompareDetailinsAllResponse;

public interface CompareDetailDAO {

    public CompareDetailinsAllResponse CompareDetailinsAll(CompareDetail req);
    public Boolean CompareDetailupdByCon(CompareDetail req);
    public Boolean CompareDetailupdDelete(CompareDetailupdDeleteReq req);
    public CompareDetailCheckReceriptResponse  CompareDetailCheckReceriptNo (CompareDetailCheckReceriptReq req);
    public Boolean ReceriptNoupdDelete(CompareDetailupdDeleteReq req);
}
