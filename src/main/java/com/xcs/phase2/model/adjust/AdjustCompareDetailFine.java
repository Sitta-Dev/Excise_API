package com.xcs.phase2.model.adjust;

import lombok.Data;

@Data
public class AdjustCompareDetailFine extends AdjustModel {

    private int FINE_ID;
    private int COMPARE_DETAIL_ID;
    private int PRODUCT_ID;
    private float FINE_RATE;
    private float VAT;
    private float FINE;
    private float NET_FINE;
    private float OLD_PAYMENT_FINE;
    private float PAYMENT_FINE;
    private float DIFFERENCE_PAYMENT_FINE;
    private float TREASURY_MONEY;
    private float BRIBE_MONEY;
    private float REWARD_MONEY;
    private int IS_ACTIVE;

    private String PRODUCT_GROUP_NAME;
    private String PRODUCT_CATEGORY_NAME;
    private String PRODUCT_TYPE_NAME;
    private String PRODUCT_SUBTYPE_NAME;
    private String PRODUCT_SUBSETTYPE_NAME;
    private String PRODUCT_BRAND_NAME_TH;
    private String PRODUCT_BRAND_NAME_EN;
    private String PRODUCT_SUBBRAND_NAME_TH;
    private String PRODUCT_SUBBRAND_NAME_EN;
    private String PRODUCT_MODEL_NAME_TH;
    private String PRODUCT_MODEL_NAME_EN;
    private float SIZES;
    private String SIZES_UNIT;
    private float QUANTITY;
    private String QUANTITY_UNIT;
    private float VOLUMN;
    private String VOLUMN_UNIT;

}
