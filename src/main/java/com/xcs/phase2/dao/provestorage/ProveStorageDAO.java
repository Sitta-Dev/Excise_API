package com.xcs.phase2.dao.provestorage;


import com.xcs.phase2.model.provestorage.ProveStorage;
import com.xcs.phase2.model.provestorage.ProveStorageEvidenceIn;
import com.xcs.phase2.model.provestorage.ProveStorageProduct;
import com.xcs.phase2.request.provestorage.ProveStoragegetByConReq;
import com.xcs.phase2.request.provestorage.ProveStoragegetByCreateReq;
import com.xcs.phase2.request.provestorage.ProveStoragegetProductReq;
import com.xcs.phase2.request.provestorage.ProveStorageupdDeleteReq;

import java.util.List;

public interface ProveStorageDAO {

    public ProveStorage ProveStoragegetByCreate(ProveStoragegetByCreateReq req);
    public List<ProveStorageProduct> ProveStorageProduct(ProveStoragegetProductReq req);
    public Boolean ProveStorageinsAll(ProveStorageEvidenceIn req);
    public ProveStorageEvidenceIn ProveStoragegetByCon(ProveStoragegetByConReq req);
    public Boolean ProveStorageupdByCon(ProveStorageEvidenceIn req);
    public Boolean ProveStorageupdDelete(ProveStorageupdDeleteReq req);
}

