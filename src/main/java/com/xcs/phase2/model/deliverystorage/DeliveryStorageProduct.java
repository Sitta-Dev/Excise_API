package com.xcs.phase2.model.deliverystorage;

import lombok.Data;

@Data
public class DeliveryStorageProduct extends DeliveryStorageModel {

    private int PRODUCT_ID;
    private int PRODUCT_GROUP_ID;
    private String PRODUCT_GROUP_CODE;
    private String PRODUCT_GROUP_NAME;
    private String PRODUCT_CATEGORY_CODE;
    private String PRODUCT_CATEGORY_NAME;
    private String PRODUCT_DESC;
    private float DEGREE;
    private float PRICE;
    private float SIZES;
    private String SIZES_UNIT;
    private float QUANTITY;
    private String QUANTITY_UNIT;
    private float VOLUMN;
    private String VOLUMN_UNIT;
    private int REMAIN_PRODUCT_ID;
    private int ARREST_PRODUCT_ID;
    private float REMAIN_SIZES;
    private String REMAIN_SIZES_UNIT;
    private float REMAIN_QUANTITY;
    private String REMAIN_QUANTITY_UNIT;
    private float REMAIN_VOLUMN;
    private String REMAIN_VOLUMN_UNIT;


}
