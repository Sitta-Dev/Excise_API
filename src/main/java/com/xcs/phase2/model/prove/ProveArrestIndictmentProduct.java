package com.xcs.phase2.model.prove;

import com.xcs.phase2.model.arrest.ArrestProduct;
import lombok.Data;

@Data
public class ProveArrestIndictmentProduct extends ProveModel {

    private int PRODUCT_INDICTMENT_ID;
    private int PRODUCT_ID;
    private int INDICTMENT_ID;
    private String PRODUCT_DESC;
    private float DEGREE;
    private float SUGAR;
    private float CO2;
    private float SIZES;
    private String SIZES_UNIT;
    private float QUANTITY;
    private String QUANTITY_UNIT;
    private float VOLUMN;
    private String VOLUMN_UNIT;

    private String PRODUCT_CODE;
    private String PRODUCT_GROUP_ID;
    private String PRODUCT_GROUP_CODE;
    private String PRODUCT_CATEGORY_ID;
    private String PRODUCT_CATEGORY_CODE;

    private ArrestProduct ArrestProduct;

    //private int IS_ILLEGAL;

}
