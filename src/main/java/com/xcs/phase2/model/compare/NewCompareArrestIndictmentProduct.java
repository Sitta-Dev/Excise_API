package com.xcs.phase2.model.compare;

import lombok.Data;

@Data
public class NewCompareArrestIndictmentProduct extends CompareModel {

    private int PRODUCT_INDICTMENT_ID;
    private int PRODUCT_ID;
    private int INDICTMENT_DETAIL_ID;
    private int PRODUCT_GROUP_ID;
    private String PRODUCT_GROUP_CODE;
    private String PRODUCT_GROUP_NAME;
    private float PRICE;
    private int SIZES_UNIT_ID;
    private int QUATITY_UNIT_ID;
    private int VOLUMN_UNIT_ID;
    private float SIZES;
    private String SIZES_UNIT;
    private float QUANTITY;
    private String QUANTITY_UNIT;
    private float VOLUMN;
    private String VOLUMN_UNIT;
    private int FINE_ESTIMATE;
    private String PRODUCT_DESC;
    private int IS_ILLEGAL;
    private int IS_ACTIVE;


}
