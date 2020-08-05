package com.xcs.phase2.dao.provestorage;


import com.xcs.phase2.model.provestorage.ProveStorageList;
import com.xcs.phase2.request.provestorage.ProvestorageListgetByConAdvReq;
import com.xcs.phase2.request.provestorage.ProvestorageListgetByKeywordReq;

import java.util.List;

public interface ProveStorageListDAO {

    public List<ProveStorageList> ProvestorageListgetByKeyword(ProvestorageListgetByKeywordReq req);
    public List<ProveStorageList> ProvestorageListgetByConAdv(ProvestorageListgetByConAdvReq req);
}
