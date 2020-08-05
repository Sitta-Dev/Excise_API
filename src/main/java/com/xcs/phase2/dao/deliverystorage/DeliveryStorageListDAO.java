package com.xcs.phase2.dao.deliverystorage;


import com.xcs.phase2.model.deliverystorage.DeliveryStorageList;
import com.xcs.phase2.request.deliverystorage.DeliverstorageListgetByConAdvReq;
import com.xcs.phase2.request.deliverystorage.DeliverstorageListgetByKeywordReq;

import java.util.List;

public interface DeliveryStorageListDAO {

    public List<DeliveryStorageList> DeliverstorageListgetByKeyword(DeliverstorageListgetByKeywordReq req);
    public List<DeliveryStorageList> DeliverstorageListgetByConAdv(DeliverstorageListgetByConAdvReq req);
}
