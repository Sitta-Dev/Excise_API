package com.xcs.phase2.dao.deliverystorage;


import com.xcs.phase2.model.deliverystorage.DeliveryStorage;
import com.xcs.phase2.model.deliverystorage.DeliveryStorageEvidenceIn;
import com.xcs.phase2.model.deliverystorage.DeliveryStorageProduct;
import com.xcs.phase2.request.deliverystorage.DeliverStoragegetByConReq;
import com.xcs.phase2.request.deliverystorage.DeliverStoragegetByCreateReq;
import com.xcs.phase2.request.deliverystorage.DeliverStoragegetProductReq;
import com.xcs.phase2.request.deliverystorage.DeliverStorageupdDeleteReq;
import com.xcs.phase2.response.deliverystorage.DeliveryStorageinsAllResponse;

import java.util.List;

public interface DeliveryStorageDAO {

    public DeliveryStorage DeliverStoragegetByCreate(DeliverStoragegetByCreateReq req);
    public List<DeliveryStorageProduct> DeliverStorageetProduct(DeliverStoragegetProductReq req);
    public DeliveryStorageinsAllResponse DeliveryStorageinsAll(DeliveryStorageEvidenceIn req);
    public DeliveryStorageEvidenceIn DeliverStoragegetByCon(DeliverStoragegetByConReq req);
    public Boolean DeliverStorageupdByCon(DeliveryStorageEvidenceIn req);
    public Boolean DeliverStorageupdDelete(DeliverStorageupdDeleteReq req);
}

