package com.xcs.phase2.model.deliverystorage;

import lombok.Data;

@Data
public class DeliveryStorageEvidenceInItem extends DeliveryStorageModel {

    private int EVIDENCE_IN_ITEM_ID;
    private int PRODUCT_GROUP_ID;
    private String EVIDENCE_IN_ITEM_CODE;
    private String PRODUCT_GROUP_CODE;
    private String PRODUCT_GROUP_NAME;
    private String PRODUCT_CATEGORY_CODE;
    private String PRODUCT_CATEGORY_NAME;
    private String PRODUCT_DESC;
    private float DEGREE;
    private float PRICE;
    private float DELIVERY_SIZE;
    private String DELIVERY_SIZE_UNIT;
    private float DELIVERY_QTY;
    private String DELIVERY_QTY_UNIT;
    private float DELIVERY_NET_VOLUMN;
    private String DELIVERY_NET_VOLUMN_UNIT;












}
